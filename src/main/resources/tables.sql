-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2025-04-26 22:58:06.375

-- tables
-- Table: beleske
CREATE TABLE beleske (
    beleske_id int  NOT NULL,
    tekst text  NULL,
    seansa_id int  NOT NULL,
    CONSTRAINT beleske_pk PRIMARY KEY (beleske_id)
);

-- Table: cena_seanse
CREATE TABLE cena_seanse (
    cena_seanse_id int  NOT NULL,
    cena double(10,2)  NOT NULL,
    datum_promene date  NOT NULL,
    CONSTRAINT cena_seanse_pk PRIMARY KEY (cena_seanse_id)
);

-- Table: centar_za_obuku
CREATE TABLE centar_za_obuku (
    centar_za_obuku_id int  NOT NULL,
    naziv nvarchar(50)  NOT NULL,
    email nvarchar(50)  NOT NULL COMMENT 'unique',
    telefon varchar(12)  NOT NULL,
    ulica nvarchar(50)  NOT NULL,
    broj int  NOT NULL,
    opstina nvarchar(30)  NOT NULL,
    CONSTRAINT centar_za_obuku_pk PRIMARY KEY (centar_za_obuku_id)
);

-- Table: fakultet
CREATE TABLE fakultet (
    fakultet_id int  NOT NULL,
    naziv nvarchar(50)  NOT NULL,
    univerzitet_id int  NOT NULL,
    CONSTRAINT fakultet_pk PRIMARY KEY (fakultet_id)
);

-- Table: kandidat
CREATE TABLE kandidat (
    kandidat_id int  NOT NULL,
    ime nvarchar(30)  NOT NULL,
    prezime nvarchar(30)  NOT NULL,
    jmbg nvarchar(13)  NOT NULL,
    datum_rodjenja date  NOT NULL,
    prebivaliste nvarchar(30)  NOT NULL,
    telefon varchar(12)  NOT NULL,
    email nvarchar(50)  NOT NULL,
    fakultet_id int  NOT NULL,
    centar_za_obuku_id int  NULL,
    studiija_id int  NOT NULL,
    supervizor_id int  NULL,
    CONSTRAINT kandidat_pk PRIMARY KEY (kandidat_id)
);

-- Table: klijent
CREATE TABLE klijent (
    klijent_id int  NOT NULL,
    ime nvarchar(30)  NOT NULL,
    prezime nvarchar(30)  NOT NULL,
    datum_rodjenja date  NOT NULL,
    pol nvarchar(10)  NOT NULL,
    email nvarchar(50)  NOT NULL,
    broj nvarchar(12)  NOT NULL,
    ranije_terapije bool  NOT NULL,
    prijava_id int  NULL,
    CONSTRAINT klijent_pk PRIMARY KEY (klijent_id)
);

-- Table: kurs_valute
CREATE TABLE kurs_valute (
    kurs_id int  NOT NULL,
    datum date  NOT NULL,
    kurs_din decimal(10,4)  NOT NULL,
    valuta_id int  NOT NULL,
    CONSTRAINT kurs_valute_pk PRIMARY KEY (kurs_id)
);

-- Table: objavljivanje_podataka
CREATE TABLE objavljivanje_podataka (
    objavljivanje_id int  NOT NULL,
    seansa_id int  NOT NULL,
    razlog enum('supervizija','prijava policiji','sud')  NOT NULL,
    datum_objave date  NOT NULL,
    CONSTRAINT objavljivanje_podataka_pk PRIMARY KEY (objavljivanje_id)
);

-- Table: oblast
CREATE TABLE oblast (
    oblasti_id int  NOT NULL,
    naziv nvarchar(30)  NOT NULL,
    CONSTRAINT oblast_pk PRIMARY KEY (oblasti_id)
);

-- Table: oblast_fakultet
CREATE TABLE oblast_fakultet (
    fakultet_id int  NOT NULL,
    oblasti_id int  NOT NULL,
    CONSTRAINT oblast_fakultet_pk PRIMARY KEY (fakultet_id,oblasti_id)
);

-- Table: oblast_test
CREATE TABLE oblast_test (
    oblast_testa_id int  NOT NULL,
    neaziv nvarchar(30)  NOT NULL,
    CONSTRAINT oblast_test_pk PRIMARY KEY (oblast_testa_id)
);

-- Table: oblast_usmerenje
CREATE TABLE oblast_usmerenje (
    oblasti_id int  NOT NULL,
    uze_usmerenje_id int  NOT NULL,
    CONSTRAINT oblast_usmerenje_pk PRIMARY KEY (oblasti_id,uze_usmerenje_id)
);

-- Table: oblasti_psihoterapije
CREATE TABLE oblasti_psihoterapije (
    oblasti_id int  NOT NULL,
    naziv nvarchar(30)  NOT NULL,
    CONSTRAINT oblasti_psihoterapije_pk PRIMARY KEY (oblasti_id)
);

-- Table: placanje
CREATE TABLE placanje (
    placanje_id int  NOT NULL,
    iznos double(10,2)  NOT NULL,
    iznos_sa_provizijom double(10,2)  NOT NULL,
    datum date  NOT NULL,
    valuta_valuta_id int  NOT NULL,
    nacin_placanja enum('kartica','gotovina')  NOT NULL,
    klijent_id int  NOT NULL,
    svrha_placanja enum('seansa','test')  NOT NULL,
    rata int  NULL,
    CONSTRAINT placanje_pk PRIMARY KEY (placanje_id)
);

-- Table: prijava
CREATE TABLE prijava (
    prijava_id int  NOT NULL,
    psihoterapeut_id int  NULL,
    kandidat_id int  NULL,
    CONSTRAINT prijava_pk PRIMARY KEY (prijava_id)
);

-- Table: psihoterapeut
CREATE TABLE psihoterapeut (
    psihoterapeut_id int  NULL,
    ime nvarchar(30)  NOT NULL,
    prezime nvarchar(30)  NOT NULL,
    jmbg nvarchar(13)  NOT NULL,
    email nvarchar(30)  NOT NULL,
    telefon nvarchar(12)  NOT NULL,
    datum_rodjenja date  NOT NULL,
    sertifikat_id int  NULL,
    struka_id int  NOT NULL,
    CONSTRAINT psihoterapeut_pk PRIMARY KEY (psihoterapeut_id)
);

-- Table: seansa
CREATE TABLE seansa (
    seansa_id int  NOT NULL,
    klijent_id int  NOT NULL,
    prva bool  NOT NULL,
    dan date  NOT NULL,
    vreme time  NOT NULL,
    vreme_trajanja int  NOT NULL,
    na_rate bool  NOT NULL,
    placeno bool  NOT NULL,
    cena_seanse_id int  NOT NULL,
    placanje_id int  NULL,
    CONSTRAINT seansa_pk PRIMARY KEY (seansa_id)
);

-- Table: sertifikat
CREATE TABLE sertifikat (
    sertifikat_id int  NOT NULL,
    datum_sertifikata date  NOT NULL,
    oblasti_id int  NOT NULL,
    CONSTRAINT sertifikat_pk PRIMARY KEY (sertifikat_id)
);

-- Table: stepen_studiija
CREATE TABLE stepen_studiija (
    stepen_studiija_id int  NOT NULL,
    naziv nvarchar(15)  NOT NULL,
    CONSTRAINT stepen_studiija_pk PRIMARY KEY (stepen_studiija_id)
);

-- Table: struka
CREATE TABLE struka (
    struka_id int  NOT NULL,
    naziv nvarchar(30)  NOT NULL,
    CONSTRAINT struka_pk PRIMARY KEY (struka_id)
);

-- Table: test
CREATE TABLE test (
    test_id int  NOT NULL,
    naziv nvarchar(30)  NOT NULL,
    cena int  NOT NULL,
    oblast_testa_id int  NOT NULL,
    CONSTRAINT test_pk PRIMARY KEY (test_id)
);

-- Table: testiranje
CREATE TABLE testiranje (
    testiranje_id int  NOT NULL,
    rezultat decimal(3,2)  NOT NULL,
    test_id int  NOT NULL,
    seansa_id int  NOT NULL,
    placanje_id int  NULL,
    CONSTRAINT testiranje_pk PRIMARY KEY (testiranje_id)
);

-- Table: univerzitet
CREATE TABLE univerzitet (
    univerzitet_id int  NOT NULL,
    naziv int  NOT NULL,
    uze_usmerenje_id int  NULL,
    CONSTRAINT univerzitet_pk PRIMARY KEY (univerzitet_id)
);

-- Table: uze_usmerenje
CREATE TABLE uze_usmerenje (
    naziv nvarchar(30)  NOT NULL,
    uze_usmerenje_id int  NOT NULL,
    CONSTRAINT uze_usmerenje_pk PRIMARY KEY (uze_usmerenje_id)
);

-- Table: valuta
CREATE TABLE valuta (
    valuta_id int  NOT NULL,
    skraceni_naziv nvarchar(5)  NOT NULL,
    pun_naziv nvarchar(20)  NOT NULL,
    CONSTRAINT valuta_pk PRIMARY KEY (valuta_id)
);

-- foreign keys
-- Reference: Fakultet_Univerzitet (table: fakultet)
ALTER TABLE fakultet ADD CONSTRAINT Fakultet_Univerzitet FOREIGN KEY Fakultet_Univerzitet (univerzitet_id)
    REFERENCES univerzitet (univerzitet_id);

-- Reference: beleske_seansa (table: beleske)
ALTER TABLE beleske ADD CONSTRAINT beleske_seansa FOREIGN KEY beleske_seansa (seansa_id)
    REFERENCES seansa (seansa_id);

-- Reference: kandidat_centar_za_obuku (table: kandidat)
ALTER TABLE kandidat ADD CONSTRAINT kandidat_centar_za_obuku FOREIGN KEY kandidat_centar_za_obuku (centar_za_obuku_id)
    REFERENCES centar_za_obuku (centar_za_obuku_id);

-- Reference: kandidat_fakultet (table: kandidat)
ALTER TABLE kandidat ADD CONSTRAINT kandidat_fakultet FOREIGN KEY kandidat_fakultet (fakultet_id)
    REFERENCES fakultet (fakultet_id);

-- Reference: kandidat_psihoterapeut (table: kandidat)
ALTER TABLE kandidat ADD CONSTRAINT kandidat_psihoterapeut FOREIGN KEY kandidat_psihoterapeut (supervizor_id)
    REFERENCES psihoterapeut (psihoterapeut_id);

-- Reference: kandidat_stepen_studiija (table: kandidat)
ALTER TABLE kandidat ADD CONSTRAINT kandidat_stepen_studiija FOREIGN KEY kandidat_stepen_studiija (studiija_id)
    REFERENCES stepen_studiija (stepen_studiija_id);

-- Reference: kurs_valute_valuta (table: kurs_valute)
ALTER TABLE kurs_valute ADD CONSTRAINT kurs_valute_valuta FOREIGN KEY kurs_valute_valuta (valuta_id)
    REFERENCES valuta (valuta_id);

-- Reference: objavljivanje_podataka_seansa (table: objavljivanje_podataka)
ALTER TABLE objavljivanje_podataka ADD CONSTRAINT objavljivanje_podataka_seansa FOREIGN KEY objavljivanje_podataka_seansa (seansa_id)
    REFERENCES seansa (seansa_id);

-- Reference: oblast_fakultet_fakultet (table: oblast_fakultet)
ALTER TABLE oblast_fakultet ADD CONSTRAINT oblast_fakultet_fakultet FOREIGN KEY oblast_fakultet_fakultet (fakultet_id)
    REFERENCES fakultet (fakultet_id);

-- Reference: oblast_fakultet_oblast (table: oblast_fakultet)
ALTER TABLE oblast_fakultet ADD CONSTRAINT oblast_fakultet_oblast FOREIGN KEY oblast_fakultet_oblast (oblasti_id)
    REFERENCES oblast (oblasti_id);

-- Reference: oblast_usmerenje_oblast (table: oblast_usmerenje)
ALTER TABLE oblast_usmerenje ADD CONSTRAINT oblast_usmerenje_oblast FOREIGN KEY oblast_usmerenje_oblast (oblasti_id)
    REFERENCES oblast (oblasti_id);

-- Reference: oblast_usmerenje_uze_usmerenje (table: oblast_usmerenje)
ALTER TABLE oblast_usmerenje ADD CONSTRAINT oblast_usmerenje_uze_usmerenje FOREIGN KEY oblast_usmerenje_uze_usmerenje (uze_usmerenje_id)
    REFERENCES uze_usmerenje (uze_usmerenje_id);

-- Reference: placanje_klijent (table: placanje)
ALTER TABLE placanje ADD CONSTRAINT placanje_klijent FOREIGN KEY placanje_klijent (klijent_id)
    REFERENCES klijent (klijent_id);

-- Reference: placanje_valuta (table: placanje)
ALTER TABLE placanje ADD CONSTRAINT placanje_valuta FOREIGN KEY placanje_valuta (valuta_valuta_id)
    REFERENCES valuta (valuta_id);

-- Reference: prijava_kandidat (table: prijava)
ALTER TABLE prijava ADD CONSTRAINT prijava_kandidat FOREIGN KEY prijava_kandidat (kandidat_id)
    REFERENCES kandidat (kandidat_id);

-- Reference: prijava_klijent (table: klijent)
ALTER TABLE klijent ADD CONSTRAINT prijava_klijent FOREIGN KEY prijava_klijent (prijava_id)
    REFERENCES prijava (prijava_id);

-- Reference: prijava_psihoterapeut (table: prijava)
ALTER TABLE prijava ADD CONSTRAINT prijava_psihoterapeut FOREIGN KEY prijava_psihoterapeut (psihoterapeut_id)
    REFERENCES psihoterapeut (psihoterapeut_id);

-- Reference: psihoterapeut_sertifikat (table: psihoterapeut)
ALTER TABLE psihoterapeut ADD CONSTRAINT psihoterapeut_sertifikat FOREIGN KEY psihoterapeut_sertifikat (sertifikat_id)
    REFERENCES sertifikat (sertifikat_id);

-- Reference: psihoterapeut_struka (table: psihoterapeut)
ALTER TABLE psihoterapeut ADD CONSTRAINT psihoterapeut_struka FOREIGN KEY psihoterapeut_struka (struka_id)
    REFERENCES struka (struka_id);

-- Reference: seansa_cena_seanse (table: seansa)
ALTER TABLE seansa ADD CONSTRAINT seansa_cena_seanse FOREIGN KEY seansa_cena_seanse (cena_seanse_id)
    REFERENCES cena_seanse (cena_seanse_id);

-- Reference: seansa_klijent (table: seansa)
ALTER TABLE seansa ADD CONSTRAINT seansa_klijent FOREIGN KEY seansa_klijent (klijent_id)
    REFERENCES klijent (klijent_id);

-- Reference: seansa_placanje (table: seansa)
ALTER TABLE seansa ADD CONSTRAINT seansa_placanje FOREIGN KEY seansa_placanje (placanje_id)
    REFERENCES placanje (placanje_id);

-- Reference: sertifikat_oblasti_psihoterapije (table: sertifikat)
ALTER TABLE sertifikat ADD CONSTRAINT sertifikat_oblasti_psihoterapije FOREIGN KEY sertifikat_oblasti_psihoterapije (oblasti_id)
    REFERENCES oblasti_psihoterapije (oblasti_id);

-- Reference: test_oblast_test (table: test)
ALTER TABLE test ADD CONSTRAINT test_oblast_test FOREIGN KEY test_oblast_test (oblast_testa_id)
    REFERENCES oblast_test (oblast_testa_id);

-- Reference: testiranje_placanje (table: testiranje)
ALTER TABLE testiranje ADD CONSTRAINT testiranje_placanje FOREIGN KEY testiranje_placanje (placanje_id)
    REFERENCES placanje (placanje_id);

-- Reference: testiranje_seansa (table: testiranje)
ALTER TABLE testiranje ADD CONSTRAINT testiranje_seansa FOREIGN KEY testiranje_seansa (seansa_id)
    REFERENCES seansa (seansa_id);

-- Reference: testiranje_test (table: testiranje)
ALTER TABLE testiranje ADD CONSTRAINT testiranje_test FOREIGN KEY testiranje_test (test_id)
    REFERENCES test (test_id);

-- Reference: univerzitet_uze_usmerenje (table: univerzitet)
ALTER TABLE univerzitet ADD CONSTRAINT univerzitet_uze_usmerenje FOREIGN KEY univerzitet_uze_usmerenje (uze_usmerenje_id)
    REFERENCES uze_usmerenje (uze_usmerenje_id);

-- End of file.

