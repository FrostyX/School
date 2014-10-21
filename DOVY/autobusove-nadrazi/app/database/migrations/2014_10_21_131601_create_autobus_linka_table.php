<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateAutobusLinkaTable extends Migration {

	public function up()
	{
		Schema::create('autobus_linka', function($table)
		{
			$table->increments('id');
			$table->integer('autobus_id')->unsigned();
			$table->integer('linka_id')->unsigned();

			$table->foreign('autobus_id')->references('id')->on('autobus');
			$table->foreign('linka_id')->references('id')->on('linka');
		});
	}

	public function down()
	{
		Schema::drop('autobus_linka');
	}

}
