<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateRidicTable extends Migration {

	public function up()
	{
		Schema::create('ridic', function($table)
		{
			$table->increments('id');
			$table->string('jmeno', 30);
			$table->string('prijmeni', 30);
		});
	}

	public function down()
	{
		Schema::drop('ridic');
	}

}
