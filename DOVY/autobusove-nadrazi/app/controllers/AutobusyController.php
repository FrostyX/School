<?php

class AutobusyController extends BaseController {

	public function getIndex()
	{
		$od = '2014-10-20';
		$do = '2014-10-22';

		$autobusy = Autobus::all($od, $do);

		return View::make('autobusy.index')
			->with('title', 'Autobusy')
			->with('autobusy', $autobusy)
			->with('od', date("j. n. Y", strtotime($od)))
			->with('do', date("j. n. Y", strtotime($do)))
		;
	}

}
