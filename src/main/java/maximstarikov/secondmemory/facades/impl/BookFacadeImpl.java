package maximstarikov.secondmemory.facades.impl;

import lombok.RequiredArgsConstructor;
import maximstarikov.secondmemory.enums.ResponseCodes;
import maximstarikov.secondmemory.facades.BookFacade;
import maximstarikov.secondmemory.model.Book;
import maximstarikov.secondmemory.model.ServiceResult;
import maximstarikov.secondmemory.model.User;
import maximstarikov.secondmemory.model.dto.BookResponse;
import maximstarikov.secondmemory.services.dao.UserService;
import maximstarikov.secondmemory.services.dto.BookEnricher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookFacadeImpl implements BookFacade {

    private final UserService userService;
    private final BookEnricher bookEnricher;

    @Override
    public BookResponse getBookById(Integer id) {
        ServiceResult<User> currentUserResult = userService.getCurrentUser();
        if (!currentUserResult.isOk()) {
            return BookResponse.error(ResponseCodes.SERVER_ERROR, "server error");
        }
        Book bookFromDb = currentUserResult.get()
                                           .getBooks()
                                           .stream()
                                           .filter(book -> book.getId() == id)
                                           .findFirst()
                                           .get();
        return bookEnricher.createResponse(bookFromDb);
    }

    @Override
    public BookResponse getAllUserBooks() {
        ServiceResult<User> currentUserResult = userService.getCurrentUser();
        if (!currentUserResult.isOk()) {
            return BookResponse.error(ResponseCodes.SERVER_ERROR, "server error");
        }
        return bookEnricher.createResponse(currentUserResult.get().getBooks());
    }
}
