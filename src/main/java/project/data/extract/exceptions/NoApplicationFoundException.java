package project.data.extract.exceptions;

import java.util.function.Supplier;

public class NoApplicationFoundException extends Exception implements Supplier<NoApplicationFoundException> {

    public NoApplicationFoundException(String message) {
        super(message);
    }

    @Override
    public NoApplicationFoundException get() {
        return null;
    }
}
