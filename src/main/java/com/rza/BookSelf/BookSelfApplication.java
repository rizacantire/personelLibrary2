package com.rza.BookSelf;

import com.rza.BookSelf.core.helper.Helper;
import com.rza.BookSelf.view.BookSelfGUI;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import javax.swing.plaf.basic.BasicComboBoxUI;

//@EnableSwagger2
@SpringBootApplication
public class BookSelfApplication {

    public static void main(String[] args) {
        //SpringApplication.run(BookSelfApplication.class, args);

        ConfigurableApplicationContext context = new SpringApplicationBuilder(BookSelfApplication.class).headless(false).run(args);
        BookSelfGUI bookSelfGUI = context.getBean(BookSelfGUI.class);
        bookSelfGUI.setVisible(true);

    }

    /*@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }*/
}
