import java.util.Scanner;

class Fahrkartenautomat
{
	public static void main(String[] args)
	{
		begruessung();
		Scanner tastatur = new Scanner(System.in);

		int zuZahlenderBetrag = fahrkartenbestellungErfassen(tastatur);
		int eingezahlterBetrag = fahrkartenBezahlen(tastatur, zuZahlenderBetrag);
		fahrkartenAusgeben();

		// 4 Rückgeldberechnung und -ausgabe
		int rueckgabebetrag = eingezahlterBetrag - zuZahlenderBetrag;
		if (rueckgabebetrag > 0)
		{
			System.out.printf("Der Rückgabebetrag in Höhe von %.2f Euro\n", rueckgabebetrag / 100.0);
			System.out.println("wird in folgenden Münzen ausgezahlt:");

			while (rueckgabebetrag >= 200)
			{ // 2-Euro-Münzen
				System.out.println("2 Euro");
				rueckgabebetrag = rueckgabebetrag - 200;
			}
			while (rueckgabebetrag >= 100)
			{ // 1-Euro-Münzen
				System.out.println("1 Euro");
				rueckgabebetrag = rueckgabebetrag - 100;
			}
			while (rueckgabebetrag >= 50)
			{ // 50-Cent-Münzen
				System.out.println("50 Cent");
				rueckgabebetrag = rueckgabebetrag - 50;
			}
			while (rueckgabebetrag >= 20)
			{ // 20-Cent-Münzen
				System.out.println("20 Cent");
				rueckgabebetrag = rueckgabebetrag - 20;
			}
			while (rueckgabebetrag >= 10)
			{ // 10-Cent-Münzen
				System.out.println("10 Cent");
				rueckgabebetrag = rueckgabebetrag - 10;
			}
			while (rueckgabebetrag >= 5)
			{ // 5-Cent-Münzen
				System.out.println("5 Cent");
				rueckgabebetrag = rueckgabebetrag - 5;
			}
		}

		System.out.println("\nVergessen Sie nicht, den Fahrschein\n" + "vor Fahrtantritt entwerten zu lassen!\n"
					   + "Wir wünschen Ihnen eine gute Fahrt.");

		tastatur.close();
	}
	public static void begruessung()
	{
		System.out.println("Herzlich willkommen!");
		System.out.println("Wählen Sie Ihre Wunschfahrkarte für Berlin AB aus:");
	}
	public static int fahrkartenbestellungErfassen(Scanner tastatur)
	{
		int zuZahlenderBetrag = 0;
		while(true)
		{
			System.out.println("  Kurzstrecke AB [2,00 EUR] (1)");
			System.out.println("  Einzelfahrschein AB [3,00 EUR] (2)");
			System.out.println("  Tageskarte AB [8,80 EUR] (3)");
			System.out.println("  4-Fahrten-Karte AB [9,40 EUR] (4)");
			System.out.println("  Bezahlen (9)\n");

			System.out.print("Ihre WahL: ");
			int ticket = tastatur.nextInt();
			if(ticket == 9)
			{
				if(zuZahlenderBetrag != 0)
				{
					break;
				}
				System.err.println("Sie müssen mindestens ein Ticket auswählen!");
			}
			else if(ticket < 1 || ticket > 4)
			{
				System.err.println("Falsche Eingabe!");
				continue;
			}

			System.out.print("Anzahl der Tickets: ");
			int anzahlTickets = tastatur.nextInt();
			switch(ticket)
			{
			case 1:
				zuZahlenderBetrag += 200 * anzahlTickets;
				break;
			case 2:
				zuZahlenderBetrag += 300 * anzahlTickets;
				break;
			case 3:
				zuZahlenderBetrag += 880 * anzahlTickets;
				break;
			case 4:
				zuZahlenderBetrag += 940 * anzahlTickets;
				break;
			}

			System.out.printf("\nZwischensumme: %.2f €\n\n", zuZahlenderBetrag / 100.0);
		}
		return zuZahlenderBetrag;
	}
	public static int fahrkartenBezahlen(Scanner tastatur, int zuZahlenderBetrag)
	{
		int eingezahlterBetrag = 0;
		while (eingezahlterBetrag < zuZahlenderBetrag)
		{
			double nochZuZahlen = (zuZahlenderBetrag - eingezahlterBetrag) / 100.0;
			System.out.printf("Noch zu zahlen: %.2f €\n", nochZuZahlen);
			System.out.print("Eingabe (mind. 5 Cent, höchstens 20 Euro): ");

			double eingeworfeneMuenze = tastatur.nextDouble();
			if(eingeworfeneMuenze != 0.05
			&& eingeworfeneMuenze != 0.10
			&& eingeworfeneMuenze != 0.20
			&& eingeworfeneMuenze != 0.50
			&& eingeworfeneMuenze != 1.00
			&& eingeworfeneMuenze != 2.00
			&& eingeworfeneMuenze != 5.00
			&& eingeworfeneMuenze != 10.00
			&& eingeworfeneMuenze != 20.00)
			{
				System.err.println(">> Kein gültiges Zahlungsmittel");
				continue;
			}
			eingezahlterBetrag = eingezahlterBetrag + (int)(eingeworfeneMuenze * 100);
		}
		return eingezahlterBetrag;
	}
	 public static void fahrkartenAusgeben()
	 {
		 System.out.println("\nFahrschein wird ausgegeben");
		 for (int i = 0; i < 8; i++)
		 {
			 System.out.print("=");
			 try
			 {
				 Thread.sleep(200);
			 }
			 catch (InterruptedException e)
			 {
				 e.printStackTrace();
			 }
		 }
		 System.out.println("\n\n");
	 }
}
