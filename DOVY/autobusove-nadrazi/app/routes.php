<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It's a breeze. Simply tell Laravel the URIs it should respond to
| and give it the Closure to execute when that URI is requested.
|
*/

Route::get('/', 'HomeController@getIndex');
Route::get('/ridici/{sort?}/{id?}', 'RidiciController@getIndex');
Route::get('/autobusy/{sort?}', 'AutobusyController@getIndex');
Route::get('/spoje', 'SpojeController@getIndex');
Route::get('/trzba', 'TrzbaController@getIndex');

Route::post('/ridici/{sort?}/{id?}', 'RidiciController@postIndex');
