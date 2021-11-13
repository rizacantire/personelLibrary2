package com.rza.BookSelf.view;

import com.rza.BookSelf.business.abstracts.AuthorService;
import com.rza.BookSelf.business.abstracts.BookService;
import com.rza.BookSelf.business.abstracts.CategoryService;
import com.rza.BookSelf.business.abstracts.PersonelBookService;
import com.rza.BookSelf.core.helper.Config;
import com.rza.BookSelf.core.helper.Helper;
import com.rza.BookSelf.core.helper.Item;
import com.rza.BookSelf.entities.concretes.Author;
import com.rza.BookSelf.entities.concretes.Book;
import com.rza.BookSelf.entities.concretes.Category;
import com.rza.BookSelf.entities.concretes.PersonelBook;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class BookSelfGUI extends JFrame {
    private BookService bookService;
    private JPanel wrapper;
    private JTabbedPane tabbedPane;
    //Kütüphane
    private JPanel pnl_library;
    private JPanel pnl_book_self;
    private JPanel pnl_authors;
    private JButton btn_book_add;
    private JButton btn_book_edit;
    private JButton btn_book_delete;
    private JScrollPane scrl_book_list;
    private JTable tbl_book_list;
    private JPanel pnl_menu;
    private JPanel pnl_book_list;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private Object[] col_books;
    private Object[] row_books;
    private DefaultTableModel mdl_books;
    private List<Book> books;
    //Kütüphane

    //Kitaplık
    private PersonelBookService personelBookService;
    private JTable tbl_book_self;

    private Object[] col_book_self;
    private Object[] row_book_self;
    private DefaultTableModel mdl_book_self;
    private List<PersonelBook> personelBooks;
    //Kitaplık

    //Yazar
    private AuthorService authorService;
    private JButton btn_author_add;
    private JButton btn_author_delete;
    private JButton btn_author_edit;
    private JTable tbl_author_list;
    private JTextField fld_author_first_name;
    private JTextField fld_author_last_name;
    private JPanel pnl_author_add;
    private JTextField fld_update_first_name;
    private JTextField fld_update_last_name;
    private JLabel lbl_author_id;
    private Object[] col_author;
    private Object[] row_author;
    private  DefaultTableModel mdl_author;
    private List<Author> authors;
    //Yazar
    //Category
    private JPanel pnl_category;
    private JTable tbl_categories;
    private JPanel pnl_category_add;
    private JTextField fld_category_name;
    private JButton btn_category_add;
    private JButton btn_category_delete;
    private JButton btn_category_edit;
    private CategoryService categoryService;
    private List<Category> categories;
    private JLabel lbl_category_id;
    private JPanel book_add_panel;
    private JTextField fld_book_name;
    private JTextField fld_book_page;
    private JComboBox cmb_authors;
    private JComboBox cmb_categories;
    private JLabel lbl_book_id;
    private Object[] col_category;
    private Object[] row_category;
    private DefaultTableModel mdl_category;
    //Category
    public BookSelfGUI(BookService bookService, PersonelBookService personelBookService, AuthorService authorService,
                       CategoryService categoryService) {
        this.bookService = bookService;
        this.personelBookService = personelBookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
        loadGUI();

        //Kütüphane
        col_books = new Object[]{"Id", "Yazar", "Kitap Adı", "Sayfa Sayısı", "Kategori"};
        mdl_books = new DefaultTableModel();
        mdl_books.setColumnIdentifiers(col_books);
        row_books = new Object[col_books.length];
        tbl_book_list.setModel(mdl_books);
        Helper.tableEditHeader(tbl_book_list);

        loadBooks();
        //Kütüphane

        //Kitaplık
        col_book_self = new Object[]{"Id", "Yazar", "Kitap Adı", "Sayfa Sayısı", "Kategori", "Okundu mu?", "Mevcut mu?"};
        mdl_book_self = new DefaultTableModel();
        mdl_book_self.setColumnIdentifiers(col_book_self);
        row_book_self = new Object[col_book_self.length];
        tbl_book_self.setModel(mdl_book_self);
        Helper.tableEditHeader(tbl_book_self);
        loadBookSelf();

        //Kitaplık

        //Yazar
        col_author = new Object[]{"Id", "Adı", "Soyadı"};
        mdl_author = new DefaultTableModel();
        mdl_author.setColumnIdentifiers(col_author);
        row_author = new Object[col_author.length];
        tbl_author_list.setModel(mdl_author);
        Helper.tableEditHeader(tbl_author_list);
        loadAuthors();
        //Yazar

        //Category
        col_category = new Object[]{"Id","Kategori"};
        mdl_category = new DefaultTableModel();
        mdl_category.setColumnIdentifiers(col_category);
        row_category = new Object[col_category.length];
        tbl_categories.setModel(mdl_category);
        Helper.tableEditHeader(tbl_categories);
        loadCategories();

        //Category
        btn_author_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var firstName = fld_author_first_name.getText();
                var lastName = fld_author_last_name.getText();
                Author author = new Author();
                author.setFirstName(firstName);
                author.setLastName(lastName);
                var isExist = authorService.isAuthorExist(author);
                var isBlank = Helper.fieldIsEmpty(fld_author_first_name,fld_author_last_name);
                if(isExist&&isBlank==false){
                    if(Helper.confirm("sure")){
                        authorService.add(author);
                        Helper.blankField(fld_author_first_name,fld_author_last_name);
                    }
                }else {
                    if (isBlank){
                        Helper.showMsg("fill");
                    }else {
                        Helper.showMsg("Yazar Sistemde Mevcut");
                        Helper.blankField(fld_author_first_name,fld_author_last_name);
                    }
                }
                loadAuthors();
                loadAuthorCombo();
            }
        });
        tbl_author_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                var id =tbl_author_list.getValueAt(tbl_author_list.getSelectedRow(),0).toString();
                var name = tbl_author_list.getValueAt(tbl_author_list.getSelectedRow(),1).toString();
                var lastName = tbl_author_list.getValueAt(tbl_author_list.getSelectedRow(),2).toString();
                fld_update_first_name.setText(name);
                fld_update_last_name.setText(lastName);
                lbl_author_id.setText(id);

            }
        });
        btn_author_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer id = Integer.parseInt(lbl_author_id.getText());
                Author a = new Author();
                a.setId(id);
                if(Helper.confirm("sure")){
                    authorService.delete(a);
                    loadAuthors();
                }
            }
        });
        btn_author_edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var authorName = fld_update_first_name.getText();
                var authorLastName = fld_update_last_name.getText();
                Integer authorId = Integer.parseInt(lbl_author_id.getText());
                Author author = new Author(authorId,authorName,authorLastName);
                if(Helper.confirm("sure")){
                    authorService.update(author);
                    loadAuthors();
                }

            }
        });
        //Category

        tbl_categories.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                var categoryName = tbl_categories.getValueAt(tbl_categories.getSelectedRow(),1).toString();
                var categoryId = tbl_categories.getValueAt(tbl_categories.getSelectedRow(),0).toString();
                lbl_category_id.setText(categoryId);
                fld_category_name.setText(categoryName);
            }
        });
        btn_category_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var categoryName = fld_category_name.getText();
                Category category = new Category();
                category.setName(categoryName);
                if (categoryService.add(category)){
                    Helper.showMsg("Kategori Eklendi");
                }
                loadCategories();
                loadCategoryCombo();
            }
        });

        //Category
        btn_category_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var id = Integer.parseInt(lbl_category_id.getText());

                if(Helper.confirm("sure")){
                    categoryService.deleteById(id);
                    loadCategories();
                }
            }
        });
        btn_category_edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var id = Integer.parseInt(lbl_category_id.getText());
                var name = fld_category_name.getText();
                Category category = new Category();
                category.setId(id);
                category.setName(name);
                if(Helper.confirm("sure")){
                    categoryService.update(category);
                    Helper.showMsg("Kategori adı güncellendi");
                }
                loadCategories();
            }
        });

        //Category

        //Book
        loadAuthorCombo();
        loadCategoryCombo();


        //Book

        cmb_authors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var s = cmb_authors.getSelectedItem();
                var a = (Item) cmb_authors.getSelectedItem();

            }
        });


        btn_book_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var author = (Item) cmb_authors.getSelectedItem();
                var category = (Item) cmb_categories.getSelectedItem();
                var bookName = fld_book_name.getText();
                int page = Integer.parseInt(fld_book_page.getText());

                Book book = new Book();
                book.setName(bookName);
                Author a =new Author();
                a.setId(author.getKey());

                Category c = new Category();
                c.setId(category.getKey());

                List<Author> au = new ArrayList<>();
                List<Category> categories1 = new ArrayList<>();
                categories1.add(c);
                au.add(a);
                book.setAuthors(au);
                book.setPage(page);
                book.setCategories(categories1);

                var isA = bookService.getByAuthor(a);
                var isExist = isA.stream().filter(b->b.getName().equals(bookName)).findAny();
                if (isExist.isEmpty()){
                    bookService.add(book);
                    Helper.showMsg("Ekleme Başarılı");
                    Helper.blankField(fld_book_name,fld_book_page);
                    loadCategoryCombo();
                    loadAuthorCombo();
                }else {
                    Helper.showMsg("Kitap Mevcut");
                }



                /*if(bookService.add(book)){
                    Helper.showMsg("Kayıt başarılı");
                    Helper.blankField(fld_book_name,fld_book_page);

                }*/
                loadBooks();
            }
        });
        tbl_book_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                var bookId = tbl_book_list.getValueAt(tbl_book_list.getSelectedRow(),0).toString();
                lbl_book_id.setText(bookId);
                var bookName =tbl_book_list.getValueAt(tbl_book_list.getSelectedRow(),2).toString();
                var authorName = tbl_book_list.getValueAt(tbl_book_list.getSelectedRow(),1);
                var categoryName = tbl_book_list.getValueAt(tbl_book_list.getSelectedRow(),4);
                var page = tbl_book_list.getValueAt(tbl_book_list.getSelectedRow(),3);

                var cat = categoryService.findByCategoryName(categoryName.toString());
                cmb_categories.setSelectedItem(new Item(cat.getId(),cat.getName()));

                var si = cmb_categories.getSelectedItem();
                System.out.println(si);
                fld_book_name.setText(bookName);
                fld_book_page.setText(page.toString());


            }
        });
        btn_book_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int bookId = Integer.parseInt(lbl_book_id.getText());
                if (bookService.deleteById(bookId)){
                    Helper.showMsg("done");
                }
                loadBooks();
            }
        });
        btn_book_edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var page = fld_book_page.getText();
                var name = fld_book_name.getText();
                var category = cmb_categories.getSelectedItem();
                var author = cmb_authors.getSelectedItem();
                System.out.println(category+" "+author);
            }
        });
    }

    private void loadCategoryCombo() {
        var categories = categoryService.getAlll();
        cmb_categories.removeAllItems();
        cmb_categories.addItem(new Item(0,""));
        for (var c : categories){
            cmb_categories.addItem(new Item(c.getId(),c.getName()));
        }

    }

    private void loadAuthorCombo() {
        var authors = authorService.getAlll();
        cmb_authors.removeAllItems();
        cmb_authors.addItem(new Item(0,""));
        for(var a : authors){
            cmb_authors.addItem(new Item(a.getId(),(a.getFirstName()+" "+a.getLastName())));
        }
    }

    private void loadGUI() {
        add(wrapper);
        setSize(900, 600);
        setTitle(Config.PROJECT_TITLE);
        int x = Helper.screenCenter("x", getSize());
        int y = Helper.screenCenter("y", getSize());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(x, y);
        setResizable(false);
        setVisible(true);
    }

    private void clearTable(JTable table) {
        DefaultTableModel clear = (DefaultTableModel) table.getModel();
        clear.setRowCount(0);
    }

    private void loadCategories() {
        clearTable(tbl_categories);
        categories = categoryService.getAlll();
        for (var c : categories){
            int i = 0;
            row_category[i++] = c.getId();
            row_category[i++] =c.getName();
            mdl_category.addRow(row_category);
        }
    }

    private void loadAuthors() {
        clearTable(tbl_author_list);
        authors = authorService.getAlll();
        for (var author : authors) {
            int i = 0;
            row_author[i++] = author.getId();
            row_author[i++] = author.getFirstName();
            row_author[i++] = author.getLastName();
            mdl_author.addRow(row_author);
        }

    }

    private void loadBookSelf() {
        clearTable(tbl_book_self);
        personelBooks = personelBookService.getAlll();
        for (var book : personelBooks) {
            int i = 0;
            row_book_self[i++] = book.getId();
            var authors = book.getBook().getAuthors();
            for (var author : authors) {
                row_book_self[i++] = author.getFirstName() + " " + author.getLastName();
            }
            row_book_self[i++] = book.getBook().getName();
            row_book_self[i++] = book.getBook().getPage();
            row_book_self[i++] = book.getBook().getCategories().get(0).getName();
            row_book_self[i++] = book.isRead() ? "Evet" : "Hayır";
            row_book_self[i++] = book.isExist() ? "Evet" : "Hayır";
            mdl_book_self.addRow(row_book_self);
        }
    }

    private void loadBooks() {
        clearTable(tbl_book_list);
        books = bookService.getAlll();
        for (var book : books) {
            int i = 0;
            row_books[i++] = book.getId();
            var authors = book.getAuthors();
            for (var a : authors) {
                row_books[i++] = a.getFirstName() + " " + a.getLastName();
            }

            row_books[i++] = book.getName();
            row_books[i++] = book.getPage();
            row_books[i++] = book.getCategories().get(0).getName();
            mdl_books.addRow(row_books);
        }
    }

}
