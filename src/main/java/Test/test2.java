package Test;

public class test2 {
    public static void main(String[] args) {
        String s1 = "hanxjchhanxuc";
        String s2 = "hanuhanxhanxut";
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        String maxStr = "";
        int max = 0;

        int len1 = s1.length();
        int len2 = s2.length();

        for (int i = 0; i < len1; i++) {
            int k = i;
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < len2; j++) {
                for (int l = j; l < len2; l++) {
                    if(chars1[k]==chars2[l]){
                        sb.append(chars1[l]);
                        k++;
                    }else {
                        if(max<sb.length()){
                            max = sb.length();
                            maxStr = sb.toString();
                        }
                        break;
                    }
                }
            }
        }
        System.out.println(maxStr);
    }
}
