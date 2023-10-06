class StringExamples {
    public static void main(String[] args) {
        daiString();
        daiCodePoints();
        abgoosht();
    }

    public static void daiString() {
        String text = "I😂DAI";
        System.out.println("The string " + text + " has " + text.length() + " characters:");
        for (int i=0; i<text.length(); i++) {
            System.out.println(text.charAt(i));
        }
    }

    public static void daiCodePoints() {    
        String text = "I😂DAI";
        int[] codepoints = text.codePoints().toArray();
        System.out.println("The string " + text + " has " + codepoints.length + " codepoints:");
        for (int i: codepoints) {
            System.out.println(Character.toChars(i));
        }
    }

    public static void abgoosht() {
        String text = "آبگوشت";
        char[] characters = text.toCharArray();
        System.out.println("The string " + text + " has " + characters.length + " characters:");
        for (char c: characters) {
            System.out.println(c);
        }
    }
}
