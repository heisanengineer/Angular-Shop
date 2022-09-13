//input is evil!
/* //Sildiğim kod
		 * //Stringler innumunated primative değişkendir. Değiştirilemez //StringBuffer
		 * değiştirilebilr string veri yapılarıdır. StringBuffer strbuf = new
		 * StringBuffer(); StringBuffer strbuf2 = new StringBuffer();
		 * //Eğer verilen ifade ( ile başlıyorsa ifade başına 1* koyarak düzeltir if
		 * (text[0] == '(') { strbuf.append('1'); strbuf.append('*');
		 * for (int i = 0; text.length > i; i++) { strbuf.append(text[i]);}
		 * if (text[0] == '(' && strbuf.charAt(0) == '1' && strbuf.charAt(1) == '*') {
		 * strbuf2.append('('); strbuf.deleteCharAt(0); strbuf.deleteCharAt(1);
		 * for (int i = 1; text.length > i; i++) { strbuf2.append(strbuf.charAt(i));} }
		 * char[] text2 = new char[strbuf2.length()];
		 * for (int i = 0; text.length > i; i++) { text2[i] = strbuf2.charAt(i); }
		 * control(text2); } else { control(text); }
		 */
package com.csb.expressionevaluation;

import java.util.Scanner;
import java.util.Stack;

public class program {

	static String stringStr = "0123456789";
	static String stringPtr = "+-/*()";
	static char[] str = stringStr.toCharArray();
	static char[] ptr = stringPtr.toCharArray();
	static int i, j;
	static char[] text;
	// stack yapısı dizinin ilk giren elemanının en sonda olduğu şekilde
	// saklamasağlar
	// sırayla üst üste konan kutular gibi düşünülebilir en son koyulan en üstte
	// çıkar
	public static Stack<Double> numbers = new Stack<Double>(); // Integer nesnesiyle int veri saklayan yığın
																// oluşturulur.
	public static Stack<Character> operators = new Stack<Character>(); // Character nesnesiyle char veri saklayan yığın
																		// oluşturulur.
	public static int countForError = 0;

	public static Double eval(String expres) {
		// main metod içinde girilen string değer express ile alınır.
		// express .toCharArray işlemiyle text dizesinin içine tek tek atılır.
		char[] text = expres.toCharArray();

		// program önce ( den önce ve ) den sonra gelen değeri kontrol eder
		// eğer direk operator belirtilmeden sayı girişi varsa countForError değeri
		// arttırılır
		control(text);

		// countForError 0 dan fazlaysa yani hata varsa geri dönüş hata mesajı olarak
		// yapılır.
		if (countForError == 0) {

			// döngü text.length'den dönen uzunluğa göre dönmeye başlar.
			// length bize string ifadenin kaç karakter olduğunu döndürür.
			for (i = 0; i < text.length; i++) {

				// gelen değer boşluk ise continue; ile işlem yapmadan atlanır.
				// input is evil.

				if (text[i] == ' ') {
					continue;
				}

				// gelen değer -1'den büyükse ve 10'dan küçükse stringbuffer'a eklenir
				// string değiştirilemez bir değişkendir. stringbuffer içeriği değiştirilebilir
				// stringdir.

				if (text[i] >= '0' && text[i] <= '9') {
					StringBuffer stringbuffer = new StringBuffer();

					// stringbuffer burada şartı sağlayan her döngüde tekrar oluşturulur.
					// çünkü while döngüsünden gelen değer numbers yığınına eklendikten sonra

					while (i < text.length && text[i] >= '0' && text[i] <= '9') {
						stringbuffer.append(text[i++]);
					}
					numbers.push(Double.parseDouble(stringbuffer.toString()));
					i--;

					// Integer.parseInt değeri int'e çevirir.
					// .toString değeri string'e çevirir
					// .append stringbuffer'ın sonuna ekleme yapar
					// .push stack'a veri eklemeyi sağlar.
				}

				// text dizesi içinde ki ( bulur ve operators stack'ine kaydeder
				else if (text[i] == '(') {
					operators.push(text[i]);
					// numbers.push(Evaluation('*', numbers.pop(),numbers.pop()));
				}

				// text dizesi içinde ) parantez kapama gelene kadar, gelen sayıları ve işlemi
				// hesaplayarak numbers stack'ine kaydeder
				// daha sonra hesaplanan bu değerleri ve operatörü siler.
				else if (text[i] == ')') {
					while (operators.peek() != '(') {

						numbers.push(Evaluation(operators.pop(), numbers.pop(), numbers.pop()));

					}

					operators.pop();

					// .pop stack'in en sonunda ki elemanı siler.
					// .peek kuyruğun en başında ki elemanı getirir
				}

				// .empty operatörüyle yığın boş mu kontrol edilir. Boşsa matematik işlemleri
				// bitmiştir.
				else if (text[i] == '+' || text[i] == '-' || text[i] == '*' || text[i] == '/') {

					while (!operators.empty() && prioritySwitch(text[i], operators.peek())) {

						numbers.push(Evaluation(operators.pop(), numbers.pop(), numbers.pop()));

					}

					operators.push(text[i]);

					// .pop stack'in en sonunda ki elemanı siler.
					// .push stack'a veri eklemeyi sağlar.
					// .peek kuyruğun en başında ki elemanı getirir.

				}
			}

			// tekrar bir kontrol yapılır ve operator yığını boş değilse işlem tamamlanır.
			while (!operators.empty()) {
				numbers.push(Evaluation(operators.pop(), numbers.pop(), numbers.pop()));
			}
			return numbers.pop();

		} else {

			System.out.println(
					"The entered text format is incorrect, must be the operator must be specified after the parentheses.Not come direct numbers."
							+ "Error Count:" + countForError);
			return 0.0;
		}

	}

	// ( den önce direk sayı gelmesi
	// ) den sonra direk sayı gelmesi burada kontrol ediliyor.
	public static void control(char arr[]) {
		for (i = 1; i < arr.length - 1; i++) {
			if (arr[i] == '(') {
				for (j = 1; j < str.length - 1; j++) {
					if (arr[i - 1] == str[j]) {
						countForError++;
					}
				}
			}
		}
		for (i = 0; i < arr.length - 1; i++) {
			if (arr[i] == ')') {
				for (j = 0; j < str.length; j++) {
					if (arr[i + 1] == str[j]) {
						countForError++;
					}
				}
			}
		}
	}

	// işlem önceliğini ayırmamızı sağlayan metot

	public static boolean prioritySwitch(char operator1, char operator2) {
		if (operator2 == '(' || operator2 == ')')
			return false;
		if ((operator1 == '*' || operator1 == '/') && (operator2 == '+' || operator2 == '-'))
			return false;
		else
			return true;
	}

	// değerlerin işlemlerinin yqpılmasını sağlayan metot
	public static Double Evaluation(char operator, Double firstNumber, Double secondNumber) {
		switch (operator) {
		case '+':
			return secondNumber + firstNumber;
		case '-':
			return secondNumber - firstNumber;
		case '*':
			return secondNumber * firstNumber;
		case '/':
			if (firstNumber == 0)
				throw new UnsupportedOperationException("Numbers are not divide by 0.");
			return secondNumber / firstNumber;
		}
		return 0.0;
	}

	// main method
	public static void main(String[] args) {

		System.out.print("\nPlease enter an expression to parse the operation---->:");

		// Scanner kullanıcıdan input almayı sağlayan nesne
		Scanner input = new Scanner(System.in);

		// Scannerden dönen veri ne olursa olsun string ifadeye çevrilip valueString'e
		// kaydedilir.
		String valueString = input.nextLine().toString();

		// eval fonksiyonuyla gönderilen string ifade işleme tabi olur.
		// eval bir int dönüş değeri sağlar. Bu dönen değer nihayet sonuç olur.

		Double result = eval(valueString);

		if (result != 0)
			System.out.println("Result:" + result);
	}

	public static void welcome() {
		System.out.println("************************************");
		System.out.println("    String to Int Parse Program     ");
		System.out.println("************************************");
		System.out.println("Evaluation Of The Entered Expression");
		System.out.println("************************************");
	}
}
