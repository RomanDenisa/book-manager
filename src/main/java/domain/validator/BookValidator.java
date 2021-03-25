package domain.validator;

import domain.Book;

/**
 * Validates a book
 */
public class BookValidator implements Validator<Book>{
    @Override
    public void validate(Book entity) throws ValidatorException {
        String errors = "";
        if(entity.getTitle().equals(""))
            errors +="Invalid Title!";
        if(entity.getAuthor().equals(""))
            errors +="Invalid Author!";
        if(entity.getFinishDate().equals(""))
            errors +="Invalid FinishDate!";
        if(entity.getRating() > 5 || entity.getRating() <= 0)
            errors +="Please enter a value between 1-5 for rating!";
        if(errors.length()>0)
            throw new ValidatorException(errors);
    }
}
