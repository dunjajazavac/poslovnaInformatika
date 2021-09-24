SET FOREIGN_KEY_CHECKS = 0;

INSERT INTO pdv_kategorija(naziv_kategorije) VALUES ('Srednja stopa pdv');
INSERT INTO pdv_kategorija(naziv_kategorije) VALUES ('Visa stopa pdv');
INSERT INTO pdv_kategorija(naziv_kategorije) VALUES ('Srednja visa pdv');
INSERT INTO pdv_kategorija(naziv_kategorije) VALUES ('Niska stopa pdv');
INSERT INTO pdv_kategorija(naziv_kategorije) VALUES ('Visoka stopa pdv');


INSERT INTO pdv_stopa(datum_vazenja, procenat, kategorija_id) VALUES ('2020-04-20', 8, 1);
INSERT INTO pdv_stopa(datum_vazenja, procenat, kategorija_id) VALUES ('2020-08-29', 18, 2);

INSERT INTO jedinica_mere(naziv_jedinice_mere, skraceni_naziv) VALUES ('komad', 'kom.');
INSERT INTO jedinica_mere(naziv_jedinice_mere, skraceni_naziv) VALUES ('gram', 'gr');
INSERT INTO jedinica_mere(naziv_jedinice_mere, skraceni_naziv) VALUES ('mililitar','ml');
INSERT INTO jedinica_mere(naziv_jedinice_mere, skraceni_naziv) VALUES ('litar','l');
INSERT INTO jedinica_mere(naziv_jedinice_mere, skraceni_naziv) VALUES ('kilogram', 'kg');

INSERT INTO grupa_robe_usluga(naziv_grupe, kategorija_id) VALUES ('kompjuteri i laptop uredjaji', 2);
INSERT INTO grupa_robe_usluga(naziv_grupe, kategorija_id) VALUES ('hemijska sredstva', 1);
INSERT INTO grupa_robe_usluga(naziv_grupe, kategorija_id) VALUES ('ostalo', 1);

INSERT INTO roba_usluga(naziv_robe_usluge, opis, roba, id_jedinice_mere, id_grupe) VALUES ('Laptop Dell Latitude','mali laptop', true, 1, 1);
INSERT INTO roba_usluga(naziv_robe_usluge, opis, roba, id_jedinice_mere, id_grupe) VALUES ('Pakovanje malih zavrtnja', 'visenamenski zavrtnji', true, 2, 3);
INSERT INTO roba_usluga(naziv_robe_usluge, opis, roba, id_jedinice_mere, id_grupe) VALUES ('ScreenClean', 'sprej za ciscenje monitora',true, 3, 2);
INSERT INTO roba_usluga(naziv_robe_usluge, opis, roba, id_jedinice_mere, id_grupe) VALUES ('Asepsol', 'sredstvo za ciscenje', true, 4, 2);
INSERT INTO roba_usluga(naziv_robe_usluge, opis, roba, id_jedinice_mere, id_grupe) VALUES ('Steinberg antifriz', 'antifriz za auto', true, 5, 2);

INSERT INTO naseljeno_mesto(naziv_mesta, ptt_broj) VALUES ('Novi Sad', 21000);
INSERT INTO naseljeno_mesto(naziv_mesta, ptt_broj) VALUES ('Beograd', 11000);
INSERT INTO naseljeno_mesto(naziv_mesta, ptt_broj) VALUES ('Zajecar', 19000);

INSERT INTO preduzece(naziv_preduzeca, adresa_preduzeca, broj_telefona, fax_preduzeca, id_mesta) VALUES ('Smart Co.', 'Vasina 12', '021123456', '123456789', 1);
INSERT INTO preduzece(naziv_preduzeca, adresa_preduzeca, broj_telefona, fax_preduzeca, id_mesta) VALUES ('Epic d.o.o.', 'Zemunska 20', '011654321', '987654321', 2);

INSERT INTO poslovni_partner(naziv_poslovnog_partnera, adresa, telefon, fax, email, vrsta_partnera, id_mesta, id_preduzeca) VALUES ('Epic d.o.o', 'Zemunska 20', '011654321', '987654321', 'epicdoo@gmail.com', 'KU', 2, 1);
INSERT INTO poslovni_partner(naziv_poslovnog_partnera, adresa, telefon, fax, email, vrsta_partnera, id_mesta, id_preduzeca) VALUES ('Smart Co.', 'Vasina 12', '021123456', '123456789', 'smartco@gmail.com', 'DO', 1, 2);

INSERT INTO cenovnik(datum_pocetka_vazenja, id_preduzeca) VALUES ('2020-01-01', 1);
INSERT INTO cenovnik(datum_pocetka_vazenja, id_preduzeca) VALUES ('2020-03-03', 2);
INSERT INTO cenovnik(datum_pocetka_vazenja, id_preduzeca) VALUES ('2020-04-04', 1);
INSERT INTO cenovnik(datum_pocetka_vazenja, id_preduzeca) VALUES ('2020-05-05', 2);
INSERT INTO cenovnik(datum_pocetka_vazenja, id_preduzeca) VALUES ('2020-06-06', 1);

INSERT INTO stavka_cenovnika(cena, id_cenovnika, id_robe_usluge) VALUES (22000, 1, 1);
INSERT INTO stavka_cenovnika(cena, id_cenovnika, id_robe_usluge) VALUES (100, 2, 2);
INSERT INTO stavka_cenovnika(cena, id_cenovnika, id_robe_usluge) VALUES (200, 3, 3);
INSERT INTO stavka_cenovnika(cena, id_cenovnika, id_robe_usluge) VALUES (600, 4, 4);
INSERT INTO stavka_cenovnika(cena, id_cenovnika, id_robe_usluge) VALUES (310, 5, 5);


INSERT INTO poslovna_godina(godina, zakljucena, id_preduzeca) VALUES (2020, false, 1);
INSERT INTO poslovna_godina(godina, zakljucena, id_preduzeca) VALUES (2018, true, 2);
INSERT INTO poslovna_godina(godina, zakljucena, id_preduzeca) VALUES (2019, true, 1);

INSERT INTO narudzbenica(broj_narudzbenice, id_preduzeca, id_poslovnog_partnera, id_godine) VALUES (1, 1, 1, 1);
INSERT INTO narudzbenica(broj_narudzbenice, id_preduzeca, id_poslovnog_partnera, id_godine) VALUES (2, 2, 2, 1);
INSERT INTO narudzbenica(broj_narudzbenice, id_preduzeca, id_poslovnog_partnera, id_godine) VALUES (3, 1, 1, 1);

INSERT INTO stavka_narudzbenice(jedinica_mere, kolicina, cena, iznos, id_narudzbenice, id_robe) VALUES ('kg', 1, 310, 310, 1, 5);
INSERT INTO stavka_narudzbenice(jedinica_mere, kolicina, cena, iznos, id_narudzbenice, id_robe) VALUES ('ml', 1, 200, 200, 2, 3);
INSERT INTO stavka_narudzbenice(jedinica_mere, kolicina, cena, iznos, id_narudzbenice, id_robe) VALUES ('l', 1, 600, 600, 3, 4);

INSERT INTO faktura(broj_fakture, datum_fakture, datum_valute, ukupna_osnovica, ukupan_pdv, ukupan_iznos, status_fakture, id_godine, id_preduzeca, id_poslovnog_partnera, id_narudzbenice) VALUES (1, '2020-08-08', '2020-08-08', 310, 10, 320, 'P', 1, 1, 1, 1);
INSERT INTO faktura(broj_fakture, datum_fakture, datum_valute, ukupna_osnovica, ukupan_pdv, ukupan_iznos, status_fakture, id_godine, id_preduzeca, id_poslovnog_partnera, id_narudzbenice) VALUES (2, '2020-09-09', '2020-09-09', 200, 15, 215, 'F', 1, 1, 1, 2);
INSERT INTO faktura(broj_fakture, datum_fakture, datum_valute, ukupna_osnovica, ukupan_pdv, ukupan_iznos, status_fakture, id_godine, id_preduzeca, id_poslovnog_partnera, id_narudzbenice) VALUES (3, '2020-09-09', '2020-09-09', 580, 20, 600, 'P', 1, 1, 1, 3);


INSERT INTO stavka_fakture(kolicina, rabat, jedinicna_cena, iznos, pdv_stopa, osnovica_za_pdv, iznos_pdv, ukupan_iznos, id_preduzeca, id_fakture, id_robe) VALUES (1, 15.5, 310, 310, 5, 295, 15, 310, 1, 1, 5);
INSERT INTO stavka_fakture(kolicina, rabat, jedinicna_cena, iznos, pdv_stopa, osnovica_za_pdv, iznos_pdv, ukupan_iznos, id_preduzeca, id_fakture, id_robe) VALUES (1, 10, 200, 200, 5, 190, 10, 200, 1, 2, 3);
INSERT INTO stavka_fakture(kolicina, rabat, jedinicna_cena, iznos, pdv_stopa, osnovica_za_pdv, iznos_pdv, ukupan_iznos, id_preduzeca, id_fakture, id_robe) VALUES (1, 30, 600, 600, 5, 570, 29, 600, 1, 3, 4);

INSERT INTO otpremnica(broj_otpremnice, kupac, adresa_isporuke, datum_isporuke, prevoznik, potpis_vozaca, primio_robu, id_fakture, id_narudzbenice) VALUES (1, 'pera', 'Vase Stajica 2, Novi Sad', '2021-10-10', 'MultiExpress', true, true, 1, 1);
INSERT INTO otpremnica(broj_otpremnice, kupac, adresa_isporuke, datum_isporuke, prevoznik, potpis_vozaca, primio_robu, id_fakture, id_narudzbenice) VALUES (2, 'ana', 'Lazina 12, Novi Sad', '2021-11-11', 'OkExpress', true, true, 2, 2);

INSERT INTO stavka_otpremnice(redni_broj_proizvoda, jedinica_mere, cena, kolicina, napomena, id_otpremnice, id_robe) VALUES (3, 'ml', 200, 1, '', 1, 5);
INSERT INTO stavka_otpremnice(redni_broj_proizvoda, jedinica_mere, cena, kolicina, napomena, id_otpremnice, id_robe) VALUES (5, 'kg', 310,  1, 'brza isporuka', 2, 3);
