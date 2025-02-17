package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import mybatis.MyBatisSessionFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import service.*;
import vo.BookVO;

import java.net.URL;
import java.util.ResourceBundle;

public class BookSearchController implements Initializable {
    @FXML private TableView<BookVO> tableView;
    @FXML private TableColumn<BookVO, String> isbnCol;
    @FXML private TableColumn<BookVO, String> titleCol;
    @FXML private TableColumn<BookVO, Integer> priceCol;
    @FXML private TableColumn<BookVO, String> authorCol;
    @FXML private TextField isbnTextField;
    @FXML private TextField titleTextField;
    @FXML private TextField priceTextField;
    @FXML private TextField authorTextField;
    @FXML private Button insertBtn;
    @FXML private TextField keywordTextField;
    @FXML private Button searchBtn;
    @FXML private Button deleteBtn;

    private BookSearchService searchService;
    private BookDeleteService deleteService;
    private BookInsertService insertService;
    private BookUpdateService updateService;

    private String deleteIsbn;

    public BookSearchController() {
        SqlSessionFactory sqlSessionFactory = MyBatisSessionFactory.getSqlSessionFactory();

        this.searchService = new BookSearchServiceOracleImpl(sqlSessionFactory);
        this.deleteService = new BookDeleteServiceOracleImpl(sqlSessionFactory);
        this.insertService = new BookInsertServiceOracleImpl(sqlSessionFactory);
        this.updateService = new BookUpdateServiceOracleImpl(sqlSessionFactory);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("bisbn"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("btitle"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("bprice"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("bauthor"));

        // tableView 초기 화면
        ObservableList<BookVO> initialList = searchService.searchBookByKeyword("");
        tableView.setItems(initialList);

        // searchBtn 이벤트
        searchBtn.setOnAction(event -> {
            ObservableList<BookVO> list = searchService.searchBookByKeyword(keywordTextField.getText());
            tableView.setItems(list);
        });

        // deleteBtn 이벤트
        // 특정 행 선택
        tableView.setRowFactory(event -> {
            TableRow<BookVO> row = new TableRow<BookVO>();
            row.setOnMouseClicked(e -> {
                if (e.getClickCount() == 1) {
                    BookVO book = row.getItem();
                    deleteIsbn = book.getBisbn();
                }
            });
            return row;
        });

        deleteBtn.setOnAction(event -> {
            deleteService.deleteBookByISBN(deleteIsbn);
            ObservableList<BookVO> list = searchService.searchBookByKeyword(keywordTextField.getText());
            tableView.setItems(list);
        });

        // insertBtn 이벤트
        insertBtn.setOnAction(event -> {
            String isbnText = isbnTextField.getText();
            String titleText = titleTextField.getText();
            String priceText = priceTextField.getText();
            String authorText = authorTextField.getText();
            if (!isbnText.isEmpty() && !titleText.isEmpty()  && !priceText.isEmpty() && !authorText.isEmpty()) {
                insertService.insertBook(
                        new BookVO(isbnText, titleText, Integer.parseInt(priceText), authorText)
                );
                ObservableList<BookVO> list = searchService.searchBookByKeyword("");
                tableView.setItems(list);

                isbnTextField.setText("");
                titleTextField.setText("");
                priceTextField.setText("");
                authorTextField.setText("");
            }
        });

        // 수정 이벤트
        // tableView 편집 가능
        tableView.setEditable(true);
        titleCol.setCellFactory(TextFieldTableCell.forTableColumn());
        priceCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        authorCol.setCellFactory(TextFieldTableCell.forTableColumn());

        titleCol.setOnEditCommit(event -> {
            updateService.updateBook(event.getRowValue().getBisbn(), "btitle", event.getNewValue());
        });
        priceCol.setOnEditCommit(event -> {
            updateService.updateBook(event.getRowValue().getBisbn(), "btitle", event.getNewValue().toString());
        });
        authorCol.setOnEditCommit(event -> {
            updateService.updateBook(event.getRowValue().getBisbn(), "btitle", event.getNewValue());
        });
    }
}
