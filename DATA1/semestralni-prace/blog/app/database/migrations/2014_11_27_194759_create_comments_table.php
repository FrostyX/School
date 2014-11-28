<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateCommentsTable extends Migration {

	public function up()
	{
		// https://www.sqlite.org/datatype3.html#datetime

		$sql =
		"
			CREATE TABLE comments (
				id INTEGER PRIMARY KEY,
				user_id INTEGER,
				post_id INTEGER,
				text TEXT,
				date TEXT,

				FOREIGN KEY(user_id) REFERENCES users(id),
				FOREIGN KEY(post_id) REFERENCES posts(id)
			)
		";
		DB::statement($sql);
	}

	public function down()
	{
		DB::statement("DROP TABLE comments");
	}

}
