package Basics;

public class AnonymousInnerClasses {

    public static void main(String[] args) {

    }

    public void function() {
        Book book = new Book("Some New Name") {
            public String something() {
                return "";
            }
        };
    }

    class Book {
        private String name;

        public Book(String name) {
            this.name = name;

        }


    }


}
