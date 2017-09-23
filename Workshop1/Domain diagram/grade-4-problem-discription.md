#Introduction
We constructed a domain model for the library. For this we have conducted an interview with a librarian from the university.
Throughout the interview we found that university's library has various roles such as managing publications (articles + thesis),
diva(publication management system), learning platform MyMoodle, and other various system which are administered by the university.

After the interview, we decided to narrow down the scope of the model - to focus on books and publications.

##Primary actors
1. Librarian
Wants to manage a book in different catalogues in fair and effective manner,
and also wants to manage publications which are generated from this university.
Wants to make books and publications visible on its catalogue.

2. Guest
Wants to search a book in university's catalogue, and also wants to borrow the book if it is the book the person had been looking for.

3. Student / professor
Wants to publish its own article to university's catalogue.


#Use cases

##1. Register a book
A librarian wants to register a book into library's catalogue.
If the book is registered, everyone in the library should be able to find the book with its title, or author's name, published year.

###Main scenario
1. The librarian wants to register a book.
2. The system asks for information of the book.
3. The librarian enters the 'metadata' of the book which includes title, author, publisher, edition, year of the publication,
and location of the book.
4. The system shows the information and asks for confirmation.
5. The librarian confirms it.
6. The system registers the book into the catalogue.
7. The librarian places the book in its shelf.

###Secondary scenario
1. Registering a book into different catalogues [Libris (national), LNU (own)]
2. Librarian selects which catalogue to register, and perform the same.


##2. Modify a book
A librarian to modify the metadata of the book from its catalogue. The system updates the changes.

##3. Remove a book
A librarian to remove the book from its catalogue. The system updates the changes.

##4. Registering a publication(online resources)
A librarian wants to register an electronic resource into library's catalogue, which includes articles from professors,
or graduation thesis from students of the university.
If it is registered, everyone who has access to the library's catalogue can search the publication
using its title, author's name, and topic.


###Main scenario
1. The librarian wants to register an electronic resource.
2. The system asks for information of the publication.
3. The librarian enters the 'metadata' of the book which includes title, author, year of the publication, and topic.
4. The system shows the information and asks for confirmation.
5. The librarian confirms it.
6. The system registers the resource into the catalogue.
7. The registered resources is available in the catalogue

###Secondary scenario
1. Register the book into different catalogues [Libris (national), LNU (own)]
2. Librarian selects which catalogue to register, and perform the same afterwards.


##5. Searching book
A guest from the library wants to find the book.

###Main scenario
1. A user enters to library catalogue's search engine.
2. Enter a keyword into the search engine, which can be all fields of the book's metadata excluding its availability, and its location.
3. A list of book that fulfills the keyword is presented.
4. A detailed information(metadata) of the book is presented when user selects a book,
which includes the location of the book and its availability.


##6. Borrowing the book.
A guest from the library wants to borrow the book.
_Precondition: a guest searched a book and found the book._
(Guest includes student, professor, and other visitors of the library.)

###Main scenario

1. The guest verifies its privilege to have a loan.
2. The guest selects books to loan.
3. The system presents the list of books which the guest wishes to borrow.
4. The guest confirms it.
5. The system presents loan duration.
6. The system registers the loan, and updates the availability of these books in the catalogue.

--

##7. Searching an electronic resource / publication
A guest from the library wants to find the publication / electronic resources.
_Precondition: a guest are located with in the territory of the university or using a proxy_

###Main scenario
1. A user enters to library catalogue's search engine.
2. Enter a keyword into the search engine, which can be all fields of the resource's metadata.
3. A list of electronic resources that fulfills the keyword is presented.
4. A detailed information(metadata) of the publication is presented when user selects it.

###Secondary scenario
  1. Viewing the publication
  2. After performing the search, user can have access to fulltext of the publication.
