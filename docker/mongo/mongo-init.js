let dbAdmin = db.getSiblingDB("admin");

dbAdmin.createUser({
  user: process.env["MONGODB_ROOT_USERNAME"],
  pwd: process.env["MONGODB_ROOT_PASSWORD"],
  roles: [{ role: "userAdminAnyDatabase", db: "admin" }],
  mechanisms: ["SCRAM-SHA-1"],
});


// Authenticate user
dbAdmin.auth({
  user: process.env["MONGODB_ROOT_USERNAME"],
  pwd: process.env["MONGODB_ROOT_PASSWORD"],
  mechanisms: ["SCRAM-SHA-1"]
});

// Create DB and collection
let db1 = new Mongo().getDB(process.env["MONGODB_DATABASE"]);

db1.createUser({
  user: process.env["MONGODB_USERNAME"],
  pwd: process.env["MONGODB_PASSWORD"],
  roles: [{ role: "readWrite", db: process.env["MONGODB_DATABASE"] }],
  mechanisms: ["SCRAM-SHA-1"],
});

db1.createCollection("admin", { capped: false });
db1.admin.insertMany([
  {
    username:  process.env["MONGODB_DEFAULT_USERNAME"],
    email:  process.env["MONGODB_DEFAULT_ADMIN_EMAIL"],
    pureEmail:  process.env["MONGODB_DEFAULT_ADMIN_EMAIL_PURE"],
    password:  process.env["MONGODB_DEFAULT_ADMIN_PASSWORD"],
    enabled:  true,
    fullName: "Administrator",
    permissions: [
            {group: "admin", key: "admin_manage", name: "Manage Admins", description: "Manage Administrators"},
            {group: "user", key: "users_manage", name: "Manage Users", description: "Manage Users"},
            {group: "payment", key: "payment_gateway_manage", name: "Manage Payment gateway", description: "Manage Payment gateway"},
            {group: "broker", key: "broker_manage", name: "Manage Broker", description: "Manage Broker"},
            {group: "stopout", key: "stopout_manage", name: "Manage Stopout", description: "Manage Stopout"},
            {group: "package", key: "package_manage", name: "Manage Packages", description: "Manage Packages"},
            {group: "order", key: "order_manage", name: "Manage Orders", description: "Manage Orders"},
            {group: "log", key: "log_manage", name: "Manage Logs", description: "Manage Logs"},
            {group: "coupon", key: "coupon_manage", name: "Manage Coupons", description: "Manage Coupons"},
            {group: "referral", key: "referral_manage", name: "Manage Referrals", description: "Manage Referrals"},
            {group: "config", key: "config_manage", name: "Manage Website Configurations", description: "Manage Website Configurations"},
            {group: "withdraw", key: "withdraw_manage", name: "Manage Withdrawals", description: "Manage Withdrawals"}


        ],
  }
]);
