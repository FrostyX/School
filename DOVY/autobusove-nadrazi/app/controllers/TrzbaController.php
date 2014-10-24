<?php

class TrzbaController extends BaseController {

	public function getIndex()
	{
		$datum = '2014-10-22';
		$linky = Linka::all();

		return View::make('trzba.index')
			->with('title', 'Tržba')
			->with('linky', $linky)
			->with('datum', date("j. n. Y", strtotime($datum)))
		;
	}

}
