# create-test-random-beans
Demo project for Random Beans inside testing methods.

Because life is too short to generate random Java beans by hand. Do not waste you time instantiation your beans inside your test
Delegate random-beans library to do it : (https://github.com/benas/random-beans)



![class](/images/class.jpg?raw=true "class")


     @Test
      public void contextLoads() {


        // create random Book

        Book book = random(Book.class);

        final Book book1 = bookRepository.save(book);

        assertThat(book1.getDescription()).isEqualToIgnoringCase(book.getDescription());

        EnhancedRandom random1 = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .seed(123L)
                .objectPoolSize(100)
                .randomizationDepth(3)
                .charset(forName("UTF-8"))
                .stringLengthRange(5, 50)
                .collectionSizeRange(0, 0)
                .scanClasspathForConcreteTypes(true)
                .overrideDefaultInitialization(false)
                .build();

        // create random User with custom random object --- list of books will have zero size ( collectionSizeRange(0, 0))
        User user = random1.nextObject(User.class);
        user.getBooks().add(book);
        final User save = userRepository.save(user);

        //if i add the the book to my user domain object  books size will be 1

        final Optional<User> userFound = userRepository.findById(save.getId());
        assertThat(userFound).isPresent();

        assertThat(userFound.get().getBooks()).isNotEmpty();
        assertThat(userFound.get().getBooks()).hasSize(1);
        assertThat(userFound.get().getBooks().get(0).getTitle()).isEqualToIgnoringCase(book1.getTitle());

    }





