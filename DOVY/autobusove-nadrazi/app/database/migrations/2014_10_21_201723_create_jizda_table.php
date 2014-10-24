<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateJizdaTable extends Migration {

	public function up()
	{
		Schema::create('jizda', function($table)
		{
			$table->increments('id');
			$table->integer('ridic_id')->unsigned();
			$table->integer('autobus_id')->unsigned();
			$table->integer('linka_id')->unsigned();
			$table->integer('trzba')->unsigned();
			$table->date('datum');

			$table->foreign('ridic_id')->references('id')->on('ridic');
			$table->foreign('autobus_id')->references('id')->on('autobus');
			$table->foreign('linka_id')->references('id')->on('linka');
		});
	}

	public function down()
	{
		Schema::drop('jizda');
	}

}
