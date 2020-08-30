SHOW search_path;
SET search_path TO public;

create table organization
(
    id       text not null
        constraint organization_pk
            primary key,
    name     text not null,
    location text
);

alter table organization
    owner to postgres;

create unique index organization_id_uindex
    on organization (id);

create table application
(
    id          text not null
        constraint application_pk
            primary key,
    name        text not null,
    category    text,
    platform_id text
);

alter table application
    owner to postgres;

create unique index application_id_uindex
    on application (id);

create table platform
(
    id   text not null
        constraint platform_pk
            primary key,
    name text not null
);

alter table platform
    owner to postgres;

create unique index platform_id_uindex
    on platform (id);


create sequence app_org_id_seq
    as integer;

alter sequence app_org_id_seq owner to postgres;

create table app_org
(
    -- Only integer types can be auto increment
    id     text default nextval('app_org_id_seq'::regclass) not null
        constraint app_org_pk
            primary key,
    org_id text                                             not null
        constraint app_org_organization_id_fk
            references organization,
    app_id text                                             not null
        constraint app_org_application_id_fk
            references application
);

alter table app_org
    owner to postgres;

create unique index app_org_id_uindex
    on app_org (id);

-- Inserts
INSERT INTO public.organization (id, name, location) VALUES ('4028e381743f1cbf01743f1cc06e0000', 'BelfastOrg', 'Belfast');
INSERT INTO public.organization (id, name, location) VALUES ('4028e381743f1cbf01743f1cc0740001', 'LondonOrg', 'London');
INSERT INTO public.organization (id, name, location) VALUES ('4028e381743f1cbf01743f1cc0740002', 'DublinOrg', 'Dublin');

INSERT INTO public.application (id, name, category, platform_id) VALUES ('4028e381743f1eb501743f1eba280002', 'MonitoringApp', 'Technology', '4028e381743f2b4301743f2b53000000');
INSERT INTO public.application (id, name, category, platform_id) VALUES ('4028e381743f1eb501743f1eba210000', 'GatewayAppOne', 'Technology', '4028e381743f2c9b01743f2ca7b00000');
INSERT INTO public.application (id, name, category, platform_id) VALUES ('4028e381743f1eb501743f1eba280001', 'GatewayAppTwo', 'Technology', '4028e381743f2c9b01743f2ca7b00000');

INSERT INTO public.platform (id, name) VALUES ('4028e381743f2b4301743f2b53000000', 'MonitoringPlatform');
INSERT INTO public.platform (id, name) VALUES ('4028e381743f2c9b01743f2ca7b00000', 'GatewayPlatform');

INSERT INTO public.app_org (id, org_id, app_id) VALUES ('2', '4028e381743f1cbf01743f1cc06e0000', '4028e381743f1eb501743f1eba210000');
INSERT INTO public.app_org (id, org_id, app_id) VALUES ('3', '4028e381743f1cbf01743f1cc06e0000', '4028e381743f1eb501743f1eba280002');
INSERT INTO public.app_org (id, org_id, app_id) VALUES ('4', '4028e381743f1cbf01743f1cc0740001', '4028e381743f1eb501743f1eba280002');
INSERT INTO public.app_org (id, org_id, app_id) VALUES ('5', '4028e381743f1cbf01743f1cc0740001', '4028e381743f1eb501743f1eba210000');
INSERT INTO public.app_org (id, org_id, app_id) VALUES ('6', '4028e381743f1cbf01743f1cc0740001', '4028e381743f1eb501743f1eba280001');
INSERT INTO public.app_org (id, org_id, app_id) VALUES ('7', '4028e381743f1cbf01743f1cc0740002', '4028e381743f1eb501743f1eba280002');
INSERT INTO public.app_org (id, org_id, app_id) VALUES ('8', '4028e381743f1cbf01743f1cc0740002', '4028e381743f1eb501743f1eba210000');
INSERT INTO public.app_org (id, org_id, app_id) VALUES ('9', '4028e381743f1cbf01743f1cc0740002', '4028e381743f1eb501743f1eba280001');