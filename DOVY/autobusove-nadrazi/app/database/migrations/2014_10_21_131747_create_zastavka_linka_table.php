<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateZastavkaLinkaTable extends Migration {

	public function up()
	{
		Schema::create('zastavka_linka', function($table)
		{
			$table->increments('id');
			$table->integer('zastavka_id')->unsigned();
			$table->integer('linka_id')->unsigned();
			$table->integer('poradi')->unsigned();

			$table->foreign('zastavka_id')->references('id')->on('zastavka');
			$table->foreign('linka_id')->references('id')->on('linka');
		});
	}

	public function down()
	{
		Schema::drop('zastavka_linka');
	}

}
