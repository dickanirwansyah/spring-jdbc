drop table user if exists;
create table user(
    id serial,
    name varchar(50),
    dept varchar(50) not null,
    salary bigint
);

drop table barang if exists;
create table barang(
    id varchar(10) unique,
    nama varchar(15),
    jumlah int not null,
    harga long not null,
    constraint pk_barang_id primary key (id)
);

drop table transaksi if exists;
create table transaksi(
    id varchar(10) unique,
    tanggal date not null,
    kode_admin varchar(10),
    constraint pk_transaksi_id primary key(id)
);

drop table transaksi_barang if exists;
create table transaksi_barang(
    transaksi_id varchar(10),
    barang_id varchar(10),
    jumlah int not null,
    harga long not null,
    constraint fk_transaksi_barang_transaksi_id foreign key (transaksi_id) references transaksi(id),
    constraint fk_transaksi_barang_barang_id foreign key (barang_id) references barang(id)
);

/** query join transaksi **/



