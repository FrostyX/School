<?php

class SpojeController extends BaseController {

	public function getIndex()
	{
		$odkud = Zastavka::find(8);
		$kam = Zastavka::find(1);

		$spoje = Spoj::between($odkud->id, $kam->id);

		return View::make('spoje.index')
			->with('title', 'Spoje')
			->with('odkud', $odkud)
			->with('kam', $kam)
			->with('spoje', $spoje);
	}

}
