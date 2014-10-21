<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateAutobusTable extends Migration {

	public function up()
	{
		Schema::create('autobus', function($table)
		{
			$table->increments('id');
			$table->string('spz', 10);
			$table->integer('spotreba');
		});
	}

	public function down()
	{
		Schema::drop('autobus');
	}

}
