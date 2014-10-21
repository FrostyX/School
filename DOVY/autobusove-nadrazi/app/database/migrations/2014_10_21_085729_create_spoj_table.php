<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateSpojTable extends Migration {

	public function up()
	{
		Schema::create('spoj', function($table)
		{
			$table->increments('id');
			$table->float('vzdalenost');
			$table->integer('cena')->unsigned();
			$table->integer('linka_id')->unsigned();
			$table->integer('odkud')->unsigned();
			$table->integer('kam')->unsigned();
			$table->time('odjezd');
			$table->time('prijezd');

			$table->foreign('odkud')->references('id')->on('zastavka');
			$table->foreign('kam')->references('id')->on('zastavka');
			$table->foreign('linka_id')->references('id')->on('linka');
		});
	}

	public function down()
	{
		Schema::drop('spoj');
	}

}
