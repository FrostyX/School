<?php

class Ridic extends BaseModel
{
	protected static $table = 'ridic';

	protected static function setProperties($result, $od=NULL, $do=NULL)
	{
		$result->ujel = static::ujel($result, $od ?: '0000-00-00', $do ?: '9999-99-99');
		$result->autobusy = static::autobusy($result, $od ?: '0000-00-00', $do ?: '9999-99-99');
		return $result;
	}

	public static function find($id)
	{
		if(!$id) return NULL;

		$table = static::$table;
		$sql = "SELECT * FROM `$table` WHERE `id`=$id";
		$results = DB::select($sql);
		return static::setProperties($results[0]);
	}

	private static function ujel($result, $od, $do)
	{
		$sql =
		"
			SELECT SUM(vzdalenost) AS `ujel`
			FROM `spoj` JOIN `jizda`
			ON `spoj`.`linka_id`=`jizda`.`linka_id`
			WHERE `ridic_id`=$result->id
			AND `datum`>='$od' AND `datum`<='$do'
		";
		return DB::select($sql)[0]->ujel ?: 0;
	}

	private static function autobusy($result, $od, $do)
	{
		$sql =
		"
			SELECT autobus.* FROM `autobus` JOIN ridic_autobus
			ON autobus.id=ridic_autobus.autobus_id
			WHERE ridic_autobus.ridic_id=$result->id
			LIMIT 1
		";
		return DB::select($sql);
	}

	public static function all($sort='id', $od=NULL, $do=NULL)
	{
		$table = static::$table;
		$sql = "SELECT * FROM `$table`";
		$results = DB::select($sql);
		foreach($results as $result)
		{
			static::setProperties($result, $od, $do);
		}
		uasort($results, function($a, $b) use (&$sort)
		{
			return strcmp($a->$sort, $b->$sort);
		});

		return $results;
	}

	public static function save($result)
	{
		if($result->id)
		{
			$sql = "UPDATE `ridic` SET `jmeno`='$result->jmeno', `prijmeni`='$result->prijmeni' WHERE `id`=$result->id";
			$sql_rel = "UPDATE `ridic_autobus` SET `autobus_id`=$result->autobus_id WHERE `ridic_id`=$result->id";
			return DB::insert($sql) && DB::insert($sql_rel);
		}
		else
		{
			$ridic_id = self::nextID();
			$sql = "INSERT INTO `ridic` (`jmeno`, `prijmeni`) VALUES ('$result->jmeno', '$result->prijmeni')";
			$sql_rel = "INSERT INTO `ridic_autobus` (`ridic_id`, `autobus_id`) VALUES ($ridic_id, $result->autobus_id)";
			return DB::update($sql) && DB::update($sql_rel);
		}
	}
}
