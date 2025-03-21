#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <oci.h>

typedef struct {
    int customer_id;
    char name[51];
    char phone[51];
    char birth_date[11];
} Customer;

void check_error();
void create();
void select();
void update();
void delete();

OCIEnv* envhp;
OCIError* errhp;
OCISvcCtx* svchp;
OCISession* usrhp;
OCIServer* srvhp;
OCIStmt* stmthp;
OCIParam* param;
OCIDefine* def1 = NULL, * def2 = NULL; * def3 = NULL; * def4 = NULL;
OCIBind* bnd1 = NULL, * bnd2 = NULL, * bnd3 = NULL, * bnd4 = NULL;
sword status;

int main() {
    char* username = "C##DEV";
    char* password = "1234";
    char* dbname = "localhost:1521/xe";

    OCIEnvCreate(&envhp, OCI_DEFAULT, NULL, NULL, NULL, NULL, 0, NULL);
    OCIHandleAlloc((dvoid*)envhp, (dvoid**)&errhp, OCI_HTYPE_ERROR, (size_t)0, (dvoid**)NULL);
    OCIHandleAlloc((dvoid*)envhp, (dvoid**)&srvhp, OCI_HTYPE_SERVER, (size_t)0, (dvoid**)NULL);
    OCIServerAttach(srvhp, errhp, (text*)dbname, strlen(dbname), OCI_DEFAULT);
    OCIHandleAlloc((dvoid*)envhp, (dvoid**)&svchp, OCI_HTYPE_SVCCTX, (size_t)0, (dvoid**)NULL);
    OCIHandleAlloc((dvoid*)envhp, (dvoid**)&usrhp, OCI_HTYPE_SESSION, (size_t)0, (dvoid**)NULL);
    OCILogon2(envhp, errhp, &svchp, (OraText*)username, (ub4)strlen(username), (OraText*)password, (ub4)strlen(password), (OraText*)dbname, (ub4)strlen(dbname), OCI_DEFAULT);
    
    int option;
    while (1) {
        printf("=== CRUD ===\n");
        printf("1. Create\n");
        printf("2. Read\n");
        printf("3. Update\n");
        printf("4. Delete\n");
        printf("5. Exit\n");
        printf("Enter Option: ");
        scanf("%d", &option);
        printf("\n");

        switch (option) {
        case 1: create(); break;
        case 2: select(); break;
        case 3: update(); break;
        case 4: delete(); break;
        case 5: return;
        default: printf("Re Enter\n");
        }
        printf("\n");
    }

    OCILogoff(svchp, errhp);
    OCIHandleFree((dvoid*)envhp, OCI_HTYPE_ENV);
    OCIHandleFree((dvoid*)errhp, OCI_HTYPE_ERROR);
    OCIHandleFree((dvoid*)svchp, OCI_HTYPE_SVCCTX);
    OCIHandleFree((dvoid*)usrhp, OCI_HTYPE_SESSION);
    OCIHandleFree((dvoid*)srvhp, OCI_HTYPE_SERVER);
    OCIHandleFree((dvoid*)stmthp, OCI_HTYPE_STMT);
    OCIHandleFree((dvoid*)param, OCI_DTYPE_PARAM);

    return 0;
}

void check_error() {
    text errbuf[512];
    sb4 errcode = 0;
    OCIErrorGet((dvoid*)errhp, (ub4)1, (text*)NULL, &errcode, errbuf, (ub4)sizeof(errbuf), OCI_HTYPE_ERROR);
    printf("Error: %s\n", errbuf);
}

void create() {
    char* insert_sql = "INSERT INTO customers (customer_id, name, phone, birth_date) VALUES (customer_seq.NEXTVAL, :1, :2, TO_DATE(:3, 'YYYY-MM-DD'))";
    OCIHandleAlloc(envhp, (void**)&stmthp, OCI_HTYPE_STMT, 0, NULL);
    OCIStmtPrepare(stmthp, errhp, (text*)insert_sql, strlen(insert_sql), OCI_NTV_SYNTAX, OCI_DEFAULT);

    Customer customer;
    printf("Enter name: ");
    scanf(" %s", customer.name);
    printf("Enter phone: ");
    scanf(" %s", customer.phone);
    printf("Enter date (YYYY-MM-DD): ");
    scanf(" %s", customer.birth_date);
    
    OCIBindByPos(stmthp, &bnd1, errhp, 1, customer.name, sizeof(customer.name), SQLT_STR, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);
    OCIBindByPos(stmthp, &bnd2, errhp, 2, customer.phone, sizeof(customer.phone), SQLT_STR, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);
    OCIBindByPos(stmthp, &bnd3, errhp, 3, customer.birth_date, sizeof(customer.birth_date), SQLT_STR, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);
    
    if (OCIStmtExecute(svchp, stmthp, errhp, 1, 0, NULL, NULL, OCI_COMMIT_ON_SUCCESS) != OCI_SUCCESS) {
        check_error(errhp);
    }
    else {
        printf("Successfully created.\n");
    }
}

void select() {
    char* select_sql = "SELECT customer_id, name, phone, birth_date FROM customers";
    OCIHandleAlloc(envhp, (void**)&stmthp, OCI_HTYPE_STMT, 0, NULL);
    OCIStmtPrepare(stmthp, errhp, (text*)select_sql, strlen(select_sql), OCI_NTV_SYNTAX, OCI_DEFAULT);

    OCIStmtExecute(svchp, stmthp, errhp, 0, 0, NULL, NULL, OCI_DEFAULT);

    Customer customer;
    OCIDefineByPos(stmthp, &def1, errhp, 1, &customer.customer_id, sizeof(customer.customer_id), SQLT_INT, NULL, NULL, NULL, OCI_DEFAULT);
    OCIDefineByPos(stmthp, &def2, errhp, 2, customer.name, sizeof(customer.name), SQLT_STR, NULL, NULL, NULL, OCI_DEFAULT);
    OCIDefineByPos(stmthp, &def3, errhp, 3, customer.phone, sizeof(customer.phone), SQLT_STR, NULL, NULL, NULL, OCI_DEFAULT);
    OCIDefineByPos(stmthp, &def4, errhp, 4, customer.birth_date, sizeof(customer.birth_date), SQLT_STR, NULL, NULL, NULL, OCI_DEFAULT);

    int flag = 0;
    while ((status = OCIStmtFetch2(stmthp, errhp, 1, OCI_DEFAULT, 0, OCI_DEFAULT)) == OCI_SUCCESS || status == OCI_SUCCESS_WITH_INFO) {
        printf("%d %s %s %s\n", customer.customer_id, customer.name, customer.phone, customer.birth_date);
        flag = 1;
    }

    if (!flag) printf("Data is Not Existed.\n");
}

void update() {
    char* update_sql = "UPDATE customers SET name = :1, phone = :2, birth_date = TO_DATE(:3, 'YYYY-MM-DD') WHERE customer_id = :4";
    OCIHandleAlloc(envhp, (void**)&stmthp, OCI_HTYPE_STMT, 0, NULL);
    OCIStmtPrepare(stmthp, errhp, (text*)update_sql, strlen(update_sql), OCI_NTV_SYNTAX, OCI_DEFAULT);

    Customer customer;
    printf("Enter customer_id: ");
    scanf("%d", &customer.customer_id);
    printf("Enter name: ");
    scanf(" %s", customer.name);
    printf("Enter phone: ");
    scanf(" %s", customer.phone);
    printf("Enter date (YYYY-MM-DD): ");
    scanf(" %s", customer.birth_date);

    OCIBindByPos(stmthp, &bnd1, errhp, 1, customer.name, sizeof(customer.name), SQLT_STR, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);
    OCIBindByPos(stmthp, &bnd2, errhp, 2, customer.phone, sizeof(customer.phone), SQLT_STR, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);
    OCIBindByPos(stmthp, &bnd3, errhp, 3, customer.birth_date, sizeof(customer.birth_date), SQLT_STR, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);
    OCIBindByPos(stmthp, &bnd4, errhp, 4, &customer.customer_id, sizeof(customer.customer_id), SQLT_INT, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);

    if (OCIStmtExecute(svchp, stmthp, errhp, 1, 0, NULL, NULL, OCI_COMMIT_ON_SUCCESS) != OCI_SUCCESS) {
        check_error(errhp);
    }
    else {
        printf("Successfully updated.\n");
    }
}

void delete() {
    char* delete_sql = "DELETE FROM customers WHERE customer_id = :1";
    OCIHandleAlloc(envhp, (void**)&stmthp, OCI_HTYPE_STMT, 0, NULL);
    OCIStmtPrepare(stmthp, errhp, (text*)delete_sql, strlen(delete_sql), OCI_NTV_SYNTAX, OCI_DEFAULT);
   
    int customer_id;
    printf("Enter customer_id: ");
    scanf("%d", &customer_id);

    OCIBindByPos(stmthp, &bnd1, errhp, 1, &customer_id, sizeof(customer_id), SQLT_INT, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);

    if (OCIStmtExecute(svchp, stmthp, errhp, 1, 0, NULL, NULL, OCI_COMMIT_ON_SUCCESS) != OCI_SUCCESS) {
        check_error(errhp);
    }
    else {
        printf("Successfully deleted.\n");
    }
}