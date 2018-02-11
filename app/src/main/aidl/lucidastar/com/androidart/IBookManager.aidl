// IBookManager.aidl
package lucidastar.com.androidart;

// Declare any non-default types here with import statements
import lucidastar.com.androidart.Book;
interface IBookManager {

List<Book> getBookList();
void addBook(in Book book);

}
