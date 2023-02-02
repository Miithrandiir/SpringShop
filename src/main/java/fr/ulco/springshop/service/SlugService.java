package fr.ulco.springshop.service;

import fr.ulco.springshop.service.core.SluggerServiceInterface;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SlugService implements SluggerServiceInterface {


    public static SlugService create(){
        return new SlugService();
    }
    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
    @Override
    public String toSlug(String input) {
        String noWhiteSpace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(noWhiteSpace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.FRANCE);
    }
}
