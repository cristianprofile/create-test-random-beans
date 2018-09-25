package com.cromero.randombeaninstantationtesting;

import com.cromero.randombeaninstantationtesting.domain.Book;
import com.cromero.randombeaninstantationtesting.domain.User;
import com.cromero.randombeaninstantationtesting.repository.BookRepository;
import com.cromero.randombeaninstantationtesting.repository.UserRepository;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static io.github.benas.randombeans.api.EnhancedRandom.random;
import static io.github.benas.randombeans.api.EnhancedRandom.randomCollectionOf;
import static java.nio.charset.Charset.forName;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RandomBeanInstantationTestingApplicationTests {



    @Autowired
    BookRepository bookRepository;


    @Autowired
    UserRepository userRepository;



    @Test
    public void createBookWIthEmptyList() {


        EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .charset(forName("UTF-8"))
                .stringLengthRange(5, 50)
                .collectionSizeRange(1, 1)
                .scanClasspathForConcreteTypes(true)
                .overrideDefaultInitialization(false)
                .build();
        User user = random.random(User.class);

        assertThat(user.getBooks()).hasSize(1);
    }


    @Test
    public void contextLoads() {


        Book book = random(Book.class);

        final Book book1 = bookRepository.save(book);

        assertThat(book1.getDescription()).isEqualToIgnoringCase(book.getDescription());


        EnhancedRandom random1 = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .seed(123L)
                .objectPoolSize(100)
                .randomizationDepth(3)
                .charset(forName("UTF-8"))
                .stringLengthRange(5, 50)
                .collectionSizeRange(1, 10)
                .scanClasspathForConcreteTypes(true)
                .overrideDefaultInitialization(false)
                .build();
        User user = random1.random(User.class);

        List<Book> books = ( List<Book>)randomCollectionOf(0, Book.class);
        books.add(book);
        user.setBooks(books);
        final User save = userRepository.save(user);
//        assertThat(save.getBooks().get(0).getTitle()).isEqualToIgnoringCase(book1.getTitle());



    }

}
