package place.search.service.placeSearch.util;

public class StringUtil {
  public static String eraseBoldTags(String name) {
    return name.replaceAll("(<b>|</b>)", "");
  }
}
