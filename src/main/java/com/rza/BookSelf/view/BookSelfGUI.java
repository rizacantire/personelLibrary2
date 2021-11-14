package com.rza.BookSelf.view;

import com.rza.BookSelf.business.abstracts.*;
import com.rza.BookSelf.core.helper.Config;
import com.rza.BookSelf.core.helper.Helper;
import com.rza.BookSelf.core.helper.Item;
import com.rza.BookSelf.entities.concretes.*;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class BookSelfGUI extends JFrame {
    private Item[] items;
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
    private JButton btn_add_favorite;
    private JButton okunduOkunmadıButton;
    private JButton varYokButton;
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
    private JCheckBox chck_is_read;
    private JCheckBox chck_is_exist;
    private JButton btn_add_book_self;
    private JButton btn_book_self_book_delete;
    private JLabel lbl_selected_book_name;
    private JLabel lbl_selected_book_id;
    private JPanel pnl_favorite_books;
    private JTable tbl_favorite_book;
    private JButton btn_delete_favorite;
    private Object[] col_category;
    private Object[] row_category;
    private DefaultTableModel mdl_category;
    //Category

    //Favorite Book
    private FavoriteBookService favoriteBookService;
    private Object[] col_favorite;
    private Object[] row_favorite;
    private DefaultTableModel mdl_favorite;
    private List<FavoriteBook> favoriteBooks;

    //Favorite Book

    public BookSelfGUI(BookService bookService, PersonelBookService personelBookService, AuthorService authorService,
                       CategoryService categoryService,FavoriteBookService favoriteBookService) {
        this.bookService = bookService;
        this.personelBookService = personelBookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.favoriteBookService = favoriteBookService;
        loadGUI();

        //Kütüphane
        col_books = new Object[]{"Id","Sayı", "Yazar", "Kitap Adı", "Sayfa", "Kategori"};
        mdl_books = new DefaultTableModel();
        mdl_books.setColumnIdentifiers(col_books);
        row_books = new Object[col_books.length];
        tbl_book_list.setModel(mdl_books);
        tbl_book_list.getColumnModel().getColumn(4).setMaxWidth(50);

        tbl_book_list.getColumnModel().getColumn(5).setMaxWidth(200);
        tableColumnAlignment(tbl_book_list,1,4);
        tbl_book_list.getColumnModel().getColumn(0).setMinWidth(0);
        tbl_book_list.getColumnModel().getColumn(0).setMaxWidth(0);
        tbl_book_list.getColumnModel().getColumn(0).setWidth(0);


        Helper.tableEditHeader(tbl_book_list,0,1);

        loadBooks();

        //Kütüphane

        //Kitaplık
        col_book_self = new Object[]{"Id", "Yazar", "Kitap Adı", "Sayfa", "Kategori", "Okundu mu?", "Mevcut mu?"};
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

        //Favorite Book

        col_favorite = new Object[]{"Id","Sayı", "Yazar", "Kitap Adı", "Sayfa", "Kategori"};
        mdl_favorite = new DefaultTableModel();
        mdl_favorite.setColumnIdentifiers(col_favorite);
        row_favorite = new Object[col_favorite.length];
        tbl_favorite_book.setModel(mdl_favorite);
        loadFavoriteBooks();
        //Favorite Book

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
                var bookName =tbl_book_list.getValueAt(tbl_book_list.getSelectedRow(),3).toString();
                var authorName = tbl_book_list.getValueAt(tbl_book_list.getSelectedRow(),2);
                var categoryName = tbl_book_list.getValueAt(tbl_book_list.getSelectedRow(),5);
                var page = tbl_book_list.getValueAt(tbl_book_list.getSelectedRow(),4);

                var cat = categoryService.findByCategoryName(categoryName.toString());
                cmb_categories.setSelectedItem(new Item(cat.getId(),cat.getName()));

                fld_book_name.setText(bookName);
                fld_book_page.setText(page.toString());
                //cmb_authors.setSelectedItem(new Item(1,authorName.toString()));
                var cmb_autors_size =cmb_authors.getItemCount();
                for(int i=0;i<cmb_autors_size;i++){
                    var it = (Item)cmb_authors.getItemAt(i);
                    if (authorName.equals(it.getValue())){
                        cmb_authors.setSelectedItem(it);
                        break;
                    }
                }
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
                var bookId = Integer.parseInt(lbl_book_id.getText());
                var page = Integer.parseInt(fld_book_page.getText());
                var name = fld_book_name.getText();
                var categoryId =((Item) cmb_categories.getSelectedItem()).getKey();
                var authorId = ((Item) cmb_authors.getSelectedItem()).getKey();
                Category category = new Category();
                category.setId(categoryId);
                List<Category> categories = new ArrayList<>();
                categories.add(category);

                Author author = new Author();
                author.setId(authorId);
                List<Author> authors = new ArrayList<>();
                authors.add(author);

                Book book = new Book();
                book.setId(bookId);
                book.setName(name);
                book.setPage(page);
                book.setCategories(categories);
                book.setAuthors(authors);



                if (Helper.confirm("sure")){
                    bookService.update(book);
                    loadBooks();
                    Helper.blankField(fld_book_name,fld_book_page);
                    lbl_book_id.setText("");
                    clearComboBox(cmb_authors,cmb_categories);
                }


            }
        });
        btn_add_book_self.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var isRead= chck_is_read.isSelected();
                var isExist = chck_is_exist.isSelected();
                var bookId = Integer.parseInt(lbl_book_id.getText());
                PersonelBook pb = new PersonelBook();
                Book book = new Book();
                book.setId(bookId);
                pb.setBook(book);
                pb.setExist(isExist);
                pb.setRead(isRead);
                if (Helper.confirm("sure")){
                    personelBookService.add(pb);
                    loadBookSelf();
                    clearComboBox(cmb_categories,cmb_authors);
                    Helper.blankField(fld_book_name,fld_book_page);
                    lbl_book_id.setText("");
                    clearCheckBox(chck_is_exist,chck_is_read);
                }
            }
        });
        tbl_book_self.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                var selected_book_name = tbl_book_self.getValueAt(tbl_book_self.getSelectedRow(),2).toString();
                var selected_book_id = tbl_book_self.getValueAt(tbl_book_self.getSelectedRow(),0).toString();
                lbl_selected_book_id.setText(selected_book_id);
                lbl_selected_book_name.setText(selected_book_name);
            }
        });
        btn_add_favorite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var personelBookId = Integer.parseInt(lbl_selected_book_id.getText());
                FavoriteBook fb = new FavoriteBook();
                PersonelBook pb = new PersonelBook();
                List<PersonelBook> personelBooks = new ArrayList<>();
                pb.setId(personelBookId);
                personelBooks.add(pb);
                fb.setPersonelBooks(personelBooks);
                favoriteBookService.add(fb);
                loadFavoriteBooks();

            }
        });
        btn_book_self_book_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var bookId = Integer.parseInt(lbl_selected_book_id.getText());
                if(Helper.confirm("sure")){
                    personelBookService.deleteById(bookId);
                    loadBookSelf();
                }
            }
        });
        okunduOkunmadıButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var pbId = Integer.parseInt(lbl_selected_book_id.getText());
                PersonelBook pb = new PersonelBook();
                var pbb = personelBookService.getById(pbId);
                pb.setId(pbId);
                if (pbb.isExist()){
                    pb.setExist(false);
                }else {
                    pb.setExist(true);
                }
                personelBookService.update(pb);
                loadBookSelf();

            }
        });
    }

    private void loadFavoriteBooks() {
        clearTable(tbl_favorite_book);
        this.favoriteBooks = favoriteBookService.getAlll();
        List<PersonelBook> personelBooks = new ArrayList<>();
        int count = 1;
        for (var fb : favoriteBooks) {
            int i = 0;
            row_favorite[i++] = fb.getId();
            row_favorite[i++] = count++;
            personelBooks = fb.getPersonelBooks();
            for (var pb : personelBooks){
                row_favorite[i++] = pb.getBook().getAuthors().get(0).getFirstName()
                        +" "+pb.getBook().getAuthors().get(0).getLastName();
                row_favorite[i++] = pb.getBook().getName();
                row_favorite[i++] = pb.getBook().getPage();
                row_favorite[i++] = pb.getBook().getCategories().get(0).getName();
            }
            mdl_favorite.addRow(row_favorite);
        }

    }

    private void tableColumnAlignment(JTable table,int... columns) {
        DefaultTableCellRenderer  tce =  new DefaultTableCellRenderer();
        tce.setHorizontalAlignment(JLabel.CENTER);
        for(int c :columns){
            table.getColumnModel().getColumn(c).setCellRenderer(tce);
        }
    }

    private void clearCheckBox(JCheckBox... boxes){
        for (var b : boxes){
            b.setSelected(false);
        }
    }

    private void clearComboBox(JComboBox... boxes){
        for (var b : boxes){
            b.setSelectedItem(new Item(0,""));
        }
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
        cmb_authors.removeAllItems();
        cmb_authors.addItem(new Item(0,""));
        for(var a : this.authors){
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
        this.authors = authorService.getAlll();
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
        int count = 1;
        for (var book : books) {
            int i = 0;
            row_books[i++] = book.getId();
            row_books[i++] = count++;
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
