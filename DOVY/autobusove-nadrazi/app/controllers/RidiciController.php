<?php

class RidiciController extends BaseController {

	public function getIndex()
	{
		$od = '2014-10-20';
		$do = '2014-10-22';

		$ridici = Ridic::all($od, $do);

		return View::make('ridici.index')
			->with('title', 'Řidiči')
			->with('od', date("j. n. Y", strtotime($od)))
			->with('do', date("j. n. Y", strtotime($do)))
			->with('ridici', $ridici)
		;
	}

}
