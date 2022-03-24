/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPBank;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author phong
 */
public class EBank {

    public void VietnameseLoginSystem() {
        Locale localeVn = new Locale("vi");
        setLocale(localeVn);
    }

    public void EnglishLoginSystem() {
        Locale localeEn = new Locale("en");
        setLocale(localeEn);
    }

    void setLocale(Locale locale) {
        loginSystem(locale);
    }

    public void loginSystem(Locale localeLang) {
        ResourceBundle bundle = ResourceBundle.getBundle("TPBank.lang", localeLang);
        GetDataInput getData = new GetDataInput();

        /*Regex
            [0-9]: Input matches a character in the range "0" to "9" 
            {10,}: Input matches 10 or more characters
         */
        String accountNumber = getData.getString(bundle.getString("Account"),
                bundle.getString("AccountError"),
                bundle.getString("AccounEmpty"), "[0-9]{10,}");

        String passWord = getData.getPassword(bundle.getString("Password"),
                bundle.getString("PasswordError"),
                bundle.getString("PasswordEmpty"));

        String captcha = generateCaptcha();
        String captchaInput;
        System.out.println(bundle.getString("Captcha") + captcha);

        //Loop until user enter a correct captcha
        do {
            captchaInput = getData.getString(bundle.getString("EnterCaptcha"),
                    "", bundle.getString("CaptchaEmpty"), "");
            //Check if user enter a captcha exist in the captcha String at the 
            //first letter or eqals with captcha
            if (captchaInput.equals(captcha)) {
                break;
            } else {
            System.err.println(bundle.getString("WrongCaptcha"));
            }
        } while (true);

        boolean accountNumberExist = checkAccountNumber(accountNumber);
        boolean passWordExist = checkPassword(accountNumber, passWord);

        /*Check if accountNumber and password of accountNumber is exist in 
        list of account*/
        if (passWordExist && accountNumberExist) {
            System.out.println(bundle.getString("LoginSuccess"));
        } else {
            System.err.println(bundle.getString("LoginFail"));
        }
    }

    private boolean checkAccountNumber(String accountNumber) {
        ArrayList<Account> accountList = listAccount();

        //Check if account list is empty
        if (accountList.isEmpty()) {
            return false;
        } else {
            //Loop through each elements of arraylist from the first to the last elememt
            for (Account account : accountList) {
                //Check if accountNumber is exist in list of account or not
                if (account.getAccountNumber().equals(accountNumber)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkPassword(String accountNumber, String password) {
        ArrayList<Account> accountList = listAccount();

        //Check if account list is empty
        if (accountList.isEmpty()) {
            return false;
        } else {
            //Loop through each elements of arraylist from the first to the last elememt
            for (Account account : accountList) {
                /*Check if accountNumber is exist in list of account and password 
                of account have number must be the same with accountNumber with 
                password of user's input login*/
                if (account.getAccountNumber().equals(accountNumber)
                        && account.getPassWord().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    private String generateCaptcha() {
        Random random = new Random();
        String captcha = "";
        String numberCharacters = "0123456789";

        char character = 'A';

        //Loop to add all uppercase and lowercase characters to numberCharacters
        for (int i = 1; i < 26; i++) {
            numberCharacters += Character.toString(character).toLowerCase() + character;
            character += 1;
        }
        //Loop until get a captcha with a correct format and length must equal 5
        do {
            //Loop through from the first to the last element of character
            for (int i = 0; i < 5; i++) {
                int characterIndex = random.nextInt(numberCharacters.length());
                captcha += numberCharacters.charAt(characterIndex) + "";
            }
            //Check if length of captcha is equals 5 then break;
            if (captcha.length() == 5) {
                break;
            }
        } while (true);
        return captcha;
    }

    private ArrayList<Account> listAccount() {
        ArrayList<Account> accountList = new ArrayList<>();
        accountList.add(new Account("0123456789", "123456ab"));
        accountList.add(new Account("0987654321", "abcd1234"));
        accountList.add(new Account("1357924680", "1234abcd"));

        return accountList;
    }
}
