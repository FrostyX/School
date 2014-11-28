<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreatePostsTable extends Migration {

	public function up()
	{
		// https://www.sqlite.org/datatype3.html#datetime

		$sql =
		"
			CREATE TABLE posts (
				id INTEGER PRIMARY KEY,
				user_id INTEGER,
				slug VARCHAR(100),
				title VARCHAR(100),
				text TEXT,
				date TEXT,

				FOREIGN KEY(user_id) REFERENCES users(id)
			)
		";
		DB::statement($sql);
	}

	public function down()
	{
		DB::statement("DROP TABLE posts");
	}

}
