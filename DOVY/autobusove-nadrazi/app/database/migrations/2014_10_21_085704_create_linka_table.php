<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateLinkaTable extends Migration {

	public function up()
	{
		Schema::create('linka', function($table)
		{
			$table->increments('id');
			$table->integer('cislo')->unique();
			// $table->integer('odkud')->unsigned();
			// $table->integer('kam')->unsigned();
                        //
			// $table->foreign('odkud')->references('id')->on('zastavka');
			// $table->foreign('kam')->references('id')->on('zastavka');
		});
	}

	public function down()
	{
		Schema::drop('linka');
	}

}
