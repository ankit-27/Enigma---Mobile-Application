package encrypt.com.enigma;

/**
 * Created by ankit on 27/9/16.
 */

public class Rotor {

        private char[] rotor1 = new char[26];
        private char[] rotor2 = new char[26];
        private char[] rotor3 = new char[26];
        private char[] rotor4 = new char[26];
        private char[] reflector = new char[26];

        private int[] r11 = {5,6,18,20,12,0,3,4,14,17,13,7,2,24,21,11,10,1,15,8,9,16,19,22,25,23};
        private int[] r21 = {25,8,10,14,2,7,19,4,6,3,16,12,0,22,1,5,17,9,11,15,18,13,24,20,23,21};
        private int[] r22 = {8,25,16,4,3,17,22,0,1,6,11,13,9,7,10,20,23,24,15,21,2,5,12,14,18,19};
        private int[] r31 = {20,21,10,16,17,1,9,11,18,2,8,5,19,15,22,24,0,25,3,7,12,6,4,13,23,14};
        private int[] r32 = {3,16,11,2,10,8,17,19,20,12,5,13,24,0,7,9,1,15,14,22,18,21,4,6,25,23};
        private int[] r41 = {17,2,14,11,10,16,8,0,12,1,24,15,21,23,19,7,3,13,4,6,5,9,18,20,22,25};
        private int[] r42 = {4,7,18,23,10,12,15,20,16,25,3,0,11,8,9,2,17,21,24,5,1,6,13,19,14,22};
        private int[] re1 = {7,9,17,15,22,25,23,4,8,2,11,18,0,13,3,6,21,24,1,5,10,12,14,16,19,20};

        private int[] logic1 = new int[26];
        private int[] logic2 = new int[26];
        private int[] logic3 = new int[26];

        public void setRotor(char r1,char r2,char r3,char r4)
        {
            char ch='A';

            //setting first elements of rotor

            rotor1[0]=r1;
            rotor2[0]=r2;
            rotor3[0]=r3;
            rotor4[0]=r4;

            //setting rest elementsof rotor

            for(int i=1;i<26;i++)
            {
                if(r1<=89)
                    rotor1[i]=++r1;
                else
                {
                    r1='A';
                    rotor1[i]=r1;
                }

                if(r2<=89)
                    rotor2[i]=++r2;
                else
                {
                    r2='A';
                    rotor2[i]=r2;
                }

                if(r3<=89)
                    rotor3[i]=++r3;
                else
                {
                    r3='A';
                    rotor3[i]=r3;
                }
                if(r4<=89)
                    rotor4[i]=++r4;
                else
                {
                    r4='A';
                    rotor4[i]=r4;
                }
            }
            //setting reflector
            for(int i=0;i<26;i++)
            {
                reflector[i]=ch++;
            }

            //setting logics

            int m = (int)r2-65;
            int n = (int)r3-65;
            int p = (int)r4-65;
            for(int i=0;i<26;i++)
            {
                if(m<=25)
                {
                    logic1[i]=r21[m];
                    m++;
                }
                else
                {
                    m=0;
                    logic1[i]=r21[m++];
                }
                if(n<=25)
                {
                    logic2[i]=r31[n];
                    n++;
                }
                else
                {
                    n=0;
                    logic2[i]=r31[n++];
                }
                if(p<=25)
                {
                    logic3[i]=r41[p];
                    p++;
                }
                else
                {
                    p=0;
                    logic3[i]=r41[p++];
                }
            }
            //LAYOUT
		/*System.out.println();
		for(int i=0;i<26;i++)
		{
			System.out.printf("%c %02d \t %02d %c %02d \t %02d %c %02d \t %02d %c %02d \t %02d %c\n", rotor1[i],r11[i],logic1[i],rotor2[i],r22[i],logic2[i],rotor3[i],r32[i],logic3[i],rotor4[i],r42[i],re1[i],reflector[i]);
		}*/
        }

        public char encrypt(char en)
        {
            int i,code;
            //forward encryption
            for(i=0;i<26;i++)
            {
                if(en==rotor1[i])
                    break;
            }
            code=r11[i];
            for(i=0;i<26;i++)
            {
                if(logic1[i]==code)
                    break;
            }
            code = r22[i];
            for(i=0;i<26;i++)
            {
                if(logic2[i]==code)
                    break;
            }
            code = r32[i];
            for(i=0;i<26;i++)
            {
                if(logic3[i]==code)
                    break;
            }
            code = r42[i];
            for(i=0;i<26;i++)
            {
                if(re1[i]==code)
                    break;
            }
            i=25-i;
            //reverse encryption
            code=re1[i];
            for(i=0;i<26;i++)
            {
                if(r42[i]==code)
                    break;
            }
            code=logic3[i];
            for(i=0;i<26;i++)
            {
                if(r32[i]==code)
                    break;
            }
            code = logic2[i];
            for(i=0;i<26;i++)
            {
                if(r22[i]==code)
                    break;
            }
            code = logic1[i];
            for(i=0;i<26;i++)
            {
                if(r11[i]==code)
                    break;
            }
            return rotor1[i]; // returning encrypted code
        }
}

