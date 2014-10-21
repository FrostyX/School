<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateRidicAutobusTable extends Migration {

	public function up()
	{
		Schema::create('ridic_autobus', function($table)
		{
			$table->increments('id');
			$table->integer('ridic_id')->unsigned();
			$table->integer('autobus_id')->unsigned();

			$table->foreign('ridic_id')->references('id')->on('ridic');
			$table->foreign('autobus_id')->references('id')->on('autobus');
		});
	}

	public function down()
	{
		Schema::drop('ridic_autobus');
	}

}
