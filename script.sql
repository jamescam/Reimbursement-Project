-- public.accounts definition

-- Drop table

-- DROP TABLE public.accounts;

CREATE TABLE public.accounts (
	user_id serial4 NOT NULL,
	email text NOT NULL,
	"password" text NOT NULL,
	salt text NOT NULL,
	user_role text NOT NULL,
	CONSTRAINT accounts_email_key UNIQUE (email),
	CONSTRAINT accounts_password_key UNIQUE (password),
	CONSTRAINT accounts_pkey PRIMARY KEY (user_id),
	CONSTRAINT accounts_salt_key UNIQUE (salt)
);

-- public.approved_reimbursements definition

-- Drop table

-- DROP TABLE public.approved_reimbursements;

CREATE TABLE public.approved_reimbursements (
	reimbursement_id int4 NULL,
	purchase_date text NOT NULL,
	description text NULL,
	total_amount varchar NULL,
	status text NULL,
	manager_email text NULL
);


-- public.approved_reimbursements foreign keys

ALTER TABLE public.approved_reimbursements ADD CONSTRAINT approved_reimbursements_reimbursement_id_fkey FOREIGN KEY (reimbursement_id) REFERENCES public.accounts(user_id);

-- public.pending definition

-- Drop table

-- DROP TABLE public.pending;

CREATE TABLE public.pending (
	id serial4 NOT NULL,
	pending_id int4 NULL,
	purchase_date text NOT NULL,
	description text NULL,
	total_amount varchar NULL,
	status text NULL,
	CONSTRAINT pending_pkey PRIMARY KEY (id)
);


-- public.pending foreign keys

ALTER TABLE public.pending ADD CONSTRAINT pending_pending_id_fkey FOREIGN KEY (pending_id) REFERENCES public.accounts(user_id);

-- public.rejected_reimbursements definition

-- Drop table

-- DROP TABLE public.rejected_reimbursements;

CREATE TABLE public.rejected_reimbursements (
	reimbursement_id int4 NULL,
	purchase_date text NOT NULL,
	description text NULL,
	total_amount varchar NULL,
	status text NULL,
	manager_email text NULL
);


-- public.rejected_reimbursements foreign keys

ALTER TABLE public.rejected_reimbursements ADD CONSTRAINT rejected_reimbursements_reimbursement_id_fkey FOREIGN KEY (reimbursement_id) REFERENCES public.accounts(user_id);