package com.kouryoushine.readinglist;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 只需定义仓库接口，在应用程序启动后，该接口在运行时会自
 * 动实现。
 */
public interface ReadingListRepository extends JpaRepository<Book, Long> {

    List<Book> findByReader(String reader);

    Book save(Book book);

    List<Book> findByAuthor(String author);



}
