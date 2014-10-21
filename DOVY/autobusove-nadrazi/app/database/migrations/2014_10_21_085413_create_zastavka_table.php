<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateZastavkaTable extends Migration {

	public function up()
	{
		Schema::create('zastavka', function($table)
		{
			$table->increments('id');
			$table->string('nazev', 100);
		});
	}

	public function down()
	{
		Schema::drop('zastavka');
	}

}
