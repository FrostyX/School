<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateUsersTable extends Migration {

	public function up()
	{
		$sql =
		"
			CREATE TABLE users (
				id INTEGER PRIMARY KEY,
				login VARCHAR(30),
				password TEXT,
				remember_token TEXT
			)
		";
		DB::statement($sql);
	}

	public function down()
	{
		DB::statement("DROP TABLE users");
	}

}
