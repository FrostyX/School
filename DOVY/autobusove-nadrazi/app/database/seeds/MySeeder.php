<?php

class MySeeder extends Seeder
{
	public function run()
	{
		DB::table('autobus_linka')->delete();
		DB::table('ridic_autobus')->delete();
		DB::table('zastavka_linka')->delete();
		DB::table('autobus')->delete();
		DB::table('linka')->delete();
		DB::table('ridic')->delete();
		DB::table('spoj')->delete();
		DB::table('zastavka')->delete();
		DB::table('jizda')->delete();

		DB::table('ridic')->insert(array(
			array('id' => '1', 'jmeno' => 'Jan', 'prijmeni' => 'Novák'),
			array('id' => '2', 'jmeno' => 'Karel', 'prijmeni' => 'Malý'),
			array('id' => '3', 'jmeno' => 'Filip', 'prijmeni' => 'Starý'),
		));

		DB::table('autobus')->insert(array(
			array('id' => '1', 'spz' => 'XXX-YYY', 'spotreba' => 40),
			array('id' => '2', 'spz' => 'YYY-ZZZ', 'spotreba' => 50),
			array('id' => '3', 'spz' => 'ZZZ-XXX', 'spotreba' => 60),
		));

		DB::table('ridic_autobus')->insert(array(
			array('id' => '1', 'ridic_id' => 1, 'autobus_id' => 1),
			array('id' => '2', 'ridic_id' => 1, 'autobus_id' => 2),
			array('id' => '3', 'ridic_id' => 2, 'autobus_id' => 3),
		));

		DB::table('zastavka')->insert(array(
			array('id' => '1',  'nazev' => 'Olomouc autobusové nádraží'),
			array('id' => '2',  'nazev' => 'Olomouc Lipenská'),
			array('id' => '3',  'nazev' => 'Velká Bystřice'),
			array('id' => '4',  'nazev' => 'Přáslavice'),
			array('id' => '5',  'nazev' => 'Daskabát'),
			array('id' => '6',  'nazev' => 'Velký Újezd'),
			array('id' => '7',  'nazev' => 'Dolní Újezd'),
			array('id' => '8',  'nazev' => 'Lipník nad Bečvou'),
			array('id' => '9',  'nazev' => 'Brno'),
			// array('id' => '10', 'nazev' => ''),
			// array('id' => '11', 'nazev' => ''),
			// array('id' => '12', 'nazev' => ''),
			// array('id' => '13', 'nazev' => ''),
			// array('id' => '14', 'nazev' => ''),
			// array('id' => '15', 'nazev' => ''),
			// array('id' => '16', 'nazev' => ''),
			// array('id' => '17', 'nazev' => ''),
		));

		DB::table('linka')->insert(array(
			array('id' => '1', 'cislo' => 890706),
			array('id' => '2', 'cislo' => 890705),
			// array('id' => '3', 'cislo' => ''),
			// array('id' => '4', 'cislo' => ''),
			// array('id' => '5', 'cislo' => ''),
			// array('id' => '6', 'cislo' => ''),
		));

		DB::table('autobus_linka')->insert(array(
			array('id' => '1', 'autobus_id' => 1, 'linka_id' => 1),
			array('id' => '2', 'autobus_id' => 2, 'linka_id' => 1),
		));

		DB::table('zastavka_linka')->insert(array(
			array('id' => '1',  'linka_id' => 1, 'zastavka_id' => 1, 'poradi' => 1),
			array('id' => '2',  'linka_id' => 1, 'zastavka_id' => 2, 'poradi' => 2),
			array('id' => '3',  'linka_id' => 1, 'zastavka_id' => 3, 'poradi' => 3),
			array('id' => '4',  'linka_id' => 1, 'zastavka_id' => 4, 'poradi' => 4),
			array('id' => '5',  'linka_id' => 1, 'zastavka_id' => 5, 'poradi' => 5),
			array('id' => '6',  'linka_id' => 1, 'zastavka_id' => 6, 'poradi' => 6),
			array('id' => '7',  'linka_id' => 1, 'zastavka_id' => 7, 'poradi' => 7),
			array('id' => '8',  'linka_id' => 1, 'zastavka_id' => 8, 'poradi' => 8),
			// array('id' => '9',  'linka_id' => 1, 'zastavka_id' => 9),
			// array('id' => '10', 'linka_id' => 1, 'zastavka_id' => 3),
			// array('id' => '11', 'linka_id' => 1, 'zastavka_id' => 3),
			// array('id' => '12', 'linka_id' => 1, 'zastavka_id' => 3),
		));

		DB::table('spoj')->insert(array(
			array('id' => '1', 'vzdalenost' => 9,  'cena' => 10, 'linka_id' => 1, 'odkud' => 1, 'kam' => 2, 'odjezd' => '10:45', 'prijezd' => '10:50'),
			array('id' => '2', 'vzdalenost' => 19, 'cena' => 20, 'linka_id' => 1, 'odkud' => 2, 'kam' => 3, 'odjezd' => '10:50', 'prijezd' => '10:54'),
			array('id' => '3', 'vzdalenost' => 4,  'cena' => 5,  'linka_id' => 1, 'odkud' => 3, 'kam' => 4, 'odjezd' => '10:54', 'prijezd' => '10:58'),
			array('id' => '4', 'vzdalenost' => 9,  'cena' => 10, 'linka_id' => 1, 'odkud' => 4, 'kam' => 5, 'odjezd' => '10:58', 'prijezd' => '11:06'),
			array('id' => '5', 'vzdalenost' => 14, 'cena' => 15, 'linka_id' => 1, 'odkud' => 5, 'kam' => 6, 'odjezd' => '11:06', 'prijezd' => '10:12'),
			array('id' => '6', 'vzdalenost' => 19, 'cena' => 20, 'linka_id' => 1, 'odkud' => 6, 'kam' => 7, 'odjezd' => '10:15', 'prijezd' => '11:22'),
			array('id' => '7', 'vzdalenost' => 17, 'cena' => 18, 'linka_id' => 1, 'odkud' => 7, 'kam' => 8, 'odjezd' => '11:22', 'prijezd' => '11:40'),

			array('id' => 8,   'vzdalenost' => 17, 'cena' => 18, 'linka_id' => 2, 'odkud' => 8, 'kam' => 1, 'odjezd' => '10:25', 'prijezd' => '11:20'),
			array('id' => 9,   'vzdalenost' => 17, 'cena' => 18, 'linka_id' => 2, 'odkud' => 8, 'kam' => 1, 'odjezd' => '10:40', 'prijezd' => '11:05'),
			array('id' => 10,  'vzdalenost' => 17, 'cena' => 18, 'linka_id' => 2, 'odkud' => 8, 'kam' => 1, 'odjezd' => '12:40', 'prijezd' => '13:40'),
			array('id' => 11,  'vzdalenost' => 17, 'cena' => 18, 'linka_id' => 2, 'odkud' => 8, 'kam' => 1, 'odjezd' => '14:10', 'prijezd' => '15:00'),
			array('id' => 12,  'vzdalenost' => 17, 'cena' => 18, 'linka_id' => 2, 'odkud' => 8, 'kam' => 1, 'odjezd' => '15:55', 'prijezd' => '16:50'),
			array('id' => 13,  'vzdalenost' => 17, 'cena' => 18, 'linka_id' => 2, 'odkud' => 8, 'kam' => 1, 'odjezd' => '17:05', 'prijezd' => '18:10'),
		));

		DB::table('jizda')->insert(array(
			array('id' => '1', 'autobus_id' => 1, 'ridic_id' => 1, 'linka_id' => 1, 'trzba' => 250, 'datum' => '2014-10-20'),
			array('id' => '2', 'autobus_id' => 1, 'ridic_id' => 2, 'linka_id' => 1, 'trzba' => 350, 'datum' => '2014-10-21'),
			array('id' => '3', 'autobus_id' => 2, 'ridic_id' => 1, 'linka_id' => 1, 'trzba' => 450, 'datum' => '2014-10-22'),
			array('id' => '4', 'autobus_id' => 2, 'ridic_id' => 2, 'linka_id' => 1, 'trzba' => 250, 'datum' => '2014-10-23'),
			array('id' => '5', 'autobus_id' => 3, 'ridic_id' => 1, 'linka_id' => 1, 'trzba' => 150, 'datum' => '2014-10-24'),
		));
	}
}
