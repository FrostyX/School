<?php

class RidiciController extends BaseController {

	public function getIndex($sort='id', $ridic_id=NULL)
	{
		$od = '2014-10-20';
		$do = '2014-10-22';

		$ridici = Ridic::all($sort, $od, $do);
		$ridic = Ridic::find($ridic_id);
		$autobusy = Autobus::all('id', $od, $do);

		return View::make('ridici.index')
			->with('title', 'Řidiči')
			->with('sort', $sort)
			->with('od', date("j. n. Y", strtotime($od)))
			->with('do', date("j. n. Y", strtotime($do)))
			->with('ridici', $ridici)
			->with('ridic', $ridic)
			->with('autobusy', $autobusy)
			->with('message', Session::get('message'))
		;
	}

	public function postIndex($sort='id', $ridic_id=NULL)
	{
		$ridic = new stdClass();
		$ridic->id = Input::get('id');
		$ridic->jmeno = Input::get('jmeno');
		$ridic->prijmeni = Input::get('prijmeni');
		$ridic->autobus_id = Input::get('autobus_id');

		$message = Ridic::save($ridic) ?
			"Řidič byl úspěšně uložen" : "Řidiče se nepodařilo uložit";

		return Redirect::to('/ridici/'.$sort.'/'.$ridic_id)
			->with('message', $message)
			->withInput();
	}

}
