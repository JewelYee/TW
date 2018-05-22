import yee.Convert;

public class Main {
    public static void main(String[] args) {
        String input1 = "how much is pish tegj sky sky ?";
//        String init1 = "sky is I";
        String init2 = "prok is V";
        String init3 = "pish is X";
        String init4 = "tegj is L";
        String init5 = "sky sky Silver is 34 Credits";
        String init6  = "sky prok Gold is 57800 Credits";
        String init7 = "pish pish Iron is 3910 Credits";
        String input2 = "how many Credits is sky prok Silver ?";
        String input3 = "how many Credits is sky prok Gold ?";
        String input4 = "how many Credits is sky prok Iron ?";
        String input5 = "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?";

        Convert convert = new Convert();
//        System.out.println(convert.judgment(init1));
        System.out.println(convert.judgment(init2));
        System.out.println(convert.judgment(init3));
        System.out.println(convert.judgment(init4));
        System.out.println(convert.judgment(init5));
        System.out.println(convert.judgment(init6));
        System.out.println(convert.judgment(init7));
        System.out.println(convert.judgment(input1));
        System.out.println(convert.judgment(input2));
        System.out.println(convert.judgment(input3));
        System.out.println(convert.judgment(input4));
        System.out.println(convert.judgment(input5));
    }
}
