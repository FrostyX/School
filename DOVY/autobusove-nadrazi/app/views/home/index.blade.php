@extends('layouts.main')
@section('content')
	<h1>Home</h1>
	<p class="lead">Právě se díváte na ročníkový projekt do předmětu KMI/DOVY -- Jakub Kadlčík</p>

	<div class="panel panel-warning">
		<div class="panel-heading">Varování</div>
		<div class="panel-body">
			Zdrojový kód má některé záměrné nedostatky. Jedná se o nekonzistentnost přirozeného jazyka. Framework je napsán v anglickém jazyce a předpokládá, že zdrojový kód projektu bude také. Ovšem abych předešel problémům s jazykovou bariérou, pojmy obsažené v zadání, jsem nepřekládal. Dále potom projekt nevyužívá žádného ORM, protože jedním z hlavních cílů předmětu je seznámení se s jazykem SQL.
		</div>
	</div>

	<h2>Zadání</h2>
	<p>Navrhněte a zpracujte aplikaci pro vedení dispečinku autobusového nádraží. Budou vedeny údaje o zastávkách, spojích, autobusech a řidičích.<br>Systém umožní prezentaci na formulářích následujících údajů:</p>
	<ul>
		<li><a href="/ridici">Přehled řidičů</a> a jimi ujetých kilometrů za zvolené časové období (od data po datum) seřazené volitelně podle jmen řidičů nebo podle ujetých kilometrů.</li>
		<li><a href="/autobusy">Přehled autobusů</a> a jejich ujeté kilometry za zvolené časové období (od data po datum), seřazené volitelně podle označení autobusů nebo podle ujetých kilometrů</li>
		<li><a href="/spoje">Výpis všech přímých spojů</a> mezi dvěma zvolenými zastávkami</li>
		<li><a href="/trzba">Přehled o tržbách</a> na jednotlivých linkách za zvolený den</li>
		<li><a href="/autobusy">Průměrná spotřeba paliva</a> jednotlivých autobusů</li>
	</ul>
@stop
