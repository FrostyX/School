<?php

class Spoj extends BaseModel
{
	protected static $table = 'spoj';

	protected static function setProperties($spoj)
	{
		$spoj->odjezd = date("H:i", strtotime($spoj->odjezd));
		$spoj->prijezd = date("H:i", strtotime($spoj->prijezd));
		$spoj->odkud = Zastavka::find($spoj->odkud);
		$spoj->kam = Zastavka::find($spoj->kam);
		return $spoj;
	}

	public static function between($odkud_id, $kam_id)
	{
		$sql = "SELECT * FROM `spoj` WHERE `odkud`=$odkud_id AND `kam`=$kam_id";
		$spoje = DB::select($sql);
		foreach($spoje as $spoj)
		{
			static::setProperties($spoj);
		}
		return $spoje;
	}
}
