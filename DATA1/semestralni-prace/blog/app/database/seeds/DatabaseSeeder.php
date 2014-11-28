<?php

class DatabaseSeeder extends Seeder {

	public function run()
	{
		Eloquent::unguard();

		$this->cleanTables();
		$this->seedUsersTable();
		$this->seedPostsTable();
		$this->seedCommentsTable();
	}

	protected function cleanTables()
	{
		// In reverse order than seeding
		DB::delete('DELETE FROM comments');
		DB::delete('DELETE FROM posts');
		DB::delete('DELETE FROM users');
	}

	protected function seedUsersTable()
	{
		DB::table('users')->insert(array(
			array('id' => 1, 'login' => 'Blangis', 'password' => Hash::make('blangis')),
			array('id' => 2, 'login' => 'Curval', 'password' => Hash::make('curval')),
			array('id' => 3, 'login' => 'Durcet', 'password' => Hash::make('durcet')),
		));
	}

	protected function seedPostsTable()
	{
		DB::insert("INSERT INTO posts (id, user_id, slug, title, date, text) VALUES
			(1, 3, 'laravel-the-best-php-framework-ever', 'Laravel - the best PHP framework ever', '2014-11-28 14:47:00.000', '$this->laravelText'),
			(2, 3, 'data1-homework', 'DATA1 - Homework', '2014-11-28 15:50:00.000', '$this->homeworkText'),
			(3, 2, 'about-me', 'About me', '2014-11-28 17:50:00.000', '$this->aboutText')
		");
	}

	protected function seedCommentsTable()
	{
		DB::insert("INSERT INTO comments (id, user_id, post_id, date, text) VALUES
			(1, 1, 1, '2014-11-28 18:47:00.000', 'Yeah, so awesome framework!'),
			(2, 1, 2, '2014-11-28 19:50:00.000', 'Almost done, man'),
			(3, 2, 2, '2014-11-28 20:50:00.000', 'Srsly? Lucky you'),
			(4, 3, 3, '2014-11-28 20:55:00.000', 'No one gives a shit about you')
		");
	}

	protected $laravelText =
	'
		<p>Laravel is a web application framework with expressive, elegant syntax. We believe development must be an enjoyable, creative experience to be truly fulfilling. Laravel attempts to take the pain out of development by easing common tasks used in the majority of web projects, such as authentication, routing, sessions, and caching.
		Laravel aims to make the development process a pleasing one for the developer without sacrificing application functionality. Happy developers make the best code. To this end, we have attempted to combine the very best of what we have seen in other web frameworks, including frameworks implemented in other languages, such as Ruby on Rails, ASP.NET MVC, and Sinatra.
		Laravel is accessible, yet powerful, providing powerful tools needed for large, robust applications. A superb inversion of control container, expressive migration system, and tightly integrated unit testing support give you the tools you need to build any application with which you are tasked.</p>
		<p>Copy-pasted from <a href="http://laravel.com/docs/4.2/introduction">http://laravel.com/docs/4.2/introduction</a></p>
	';

	protected $homeworkText =
	'
		<h2>Zadání</h2>
		<p>S využitím SQLite (případně lokální instalace PostgreSQL) v oblíbeném jazyce naprogramujte jednoduchou aplikaci, která bude využívat databázi (na GUI aplikace příliš nezáleží). Termín je do pondělí 15. 12. 2014, 23:59. Můžete spolupracovat ve skupinách (max. 5 lidí).
		V databázi by měly být alespoň 3 tabulky se správně definovanými cizími klíči.</p>

		<h2>Bezpečnost hesel v databázích</h2>
		<p>Pokud budete mít tabulku uživatelů, neukládejte do databáze heslo v otevřeném textu. Ukládejte vždy pouze osolený hash a využívejte "pomalé" hashovací funkce (md5 nebo sha1 jsou nevhodné). O problematice se lze více dozvědět zde nebo zde (starší zdroj). Pro účely cvičení postačí, když použijete libovolnou hashovací funkci bez soli.
		SQL Injection</p>
		<p>Veškerá data od uživatele, která chceme využít v SQL dotazech, nemůžeme do dotazů vložit přímo. Hrozí totiž útok SQL Injection, kdy uživatel aplikaci podstrčí útočný SQL kód, který je vykonán a útočník může získat data z databáze, databázi poškodit, apod. Tomuto útoku se lze vyhnout někteou z následujících možností:</p>
		<ul>
			<li>použitím parametrizovaných dotazů (např. sqlite3_prepare),</li>
			<li>escapováním vkládaných hodnot pomocí funkce, kterou poskytuje databázový interface,</li>
			<li>v některých případech validováním dat.</li>
		</ul>

		<p>Copy-pasted from <a href="http://ondrejvaverka.com/vyuka/1415w/data1/10_home.php">http://ondrejvaverka.com/vyuka/1415w/data1/10_home.php</a></p>
	';

	protected $aboutText =
	'
		<p>My name is Jakub Kadlčík, but you may also know me as FrostyX. I am 21 years old and live around Olomouc city in Czech Republic. I was born in Jeseník so I love mountains and the nature. In the not so distant past I loved sports (fitness, basketball, cycling, jogging), healthy lifestyle and healthy food. Then the university changed my life. Now I am just the IT guy who lifts only his laptop and runs mainly after leaving bus. At least I still have delight in walks.</p>
		<p>Copy-pasted from <a href="http://frostyx.cz/about">http://frostyx.cz/about</a></p>
	';
}
