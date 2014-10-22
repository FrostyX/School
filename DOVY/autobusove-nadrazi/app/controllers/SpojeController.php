<?php

class SpojeController extends BaseController {

	public function getIndex()
	{
		$odkud = Spoj::find(8);
		$kam = Spoj::find(1);

		$spoje = Spoj::between($odkud->id, $kam->id);

		return View::make('spoje.index')
			->with('odkud', $odkud)
			->with('kam', $kam)
			->with('spoje', $spoje);
	}

}
