package com.example.cinemaxapp.core.data.local.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.example.cinemaxapp.core.data.local.database.dao.CreditDao;
import com.example.cinemaxapp.core.data.local.database.dao.CreditDao_Impl;
import com.example.cinemaxapp.core.data.local.database.dao.GenreDao;
import com.example.cinemaxapp.core.data.local.database.dao.GenreDao_Impl;
import com.example.cinemaxapp.core.data.local.database.dao.MovieDao;
import com.example.cinemaxapp.core.data.local.database.dao.MovieDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class CinemaxDatabase_Impl extends CinemaxDatabase {
  private volatile MovieDao _movieDao;

  private volatile GenreDao _genreDao;

  private volatile CreditDao _creditDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(3) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `movies` (`id` INTEGER NOT NULL, `title` TEXT NOT NULL, `backdrop_path` TEXT, `poster_path` TEXT, `release_date` TEXT, `vote_average` REAL, `overview` TEXT DEFAULT '', `runtime` INTEGER DEFAULT 0, `tagline` TEXT DEFAULT '', `homepage` TEXT DEFAULT '', PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `genres` (`id` INTEGER NOT NULL, `name` TEXT NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `movie_genre_cross_ref` (`movie_id` INTEGER NOT NULL, `genre_id` INTEGER NOT NULL, PRIMARY KEY(`movie_id`, `genre_id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `now_playing_movies_ref` (`movie_id` INTEGER NOT NULL, PRIMARY KEY(`movie_id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `credits` (`person_id` INTEGER NOT NULL, `name` TEXT NOT NULL, `profile_path` TEXT, PRIMARY KEY(`person_id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `movie_credit_ref` (`credit_id` TEXT NOT NULL, `movie_id` INTEGER NOT NULL, `person_id` INTEGER NOT NULL, `credit_type` TEXT NOT NULL, `character` TEXT, `job` TEXT, `department` TEXT, `cast_order` INTEGER NOT NULL, PRIMARY KEY(`credit_id`), FOREIGN KEY(`movie_id`) REFERENCES `movies`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`person_id`) REFERENCES `credits`(`person_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_movie_credit_ref_movie_id` ON `movie_credit_ref` (`movie_id`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_movie_credit_ref_person_id` ON `movie_credit_ref` (`person_id`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'ca87a252bcfc60904efbf6ab6b6f3be9')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `movies`");
        db.execSQL("DROP TABLE IF EXISTS `genres`");
        db.execSQL("DROP TABLE IF EXISTS `movie_genre_cross_ref`");
        db.execSQL("DROP TABLE IF EXISTS `now_playing_movies_ref`");
        db.execSQL("DROP TABLE IF EXISTS `credits`");
        db.execSQL("DROP TABLE IF EXISTS `movie_credit_ref`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsMovies = new HashMap<String, TableInfo.Column>(10);
        _columnsMovies.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovies.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovies.put("backdrop_path", new TableInfo.Column("backdrop_path", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovies.put("poster_path", new TableInfo.Column("poster_path", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovies.put("release_date", new TableInfo.Column("release_date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovies.put("vote_average", new TableInfo.Column("vote_average", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovies.put("overview", new TableInfo.Column("overview", "TEXT", false, 0, "''", TableInfo.CREATED_FROM_ENTITY));
        _columnsMovies.put("runtime", new TableInfo.Column("runtime", "INTEGER", false, 0, "0", TableInfo.CREATED_FROM_ENTITY));
        _columnsMovies.put("tagline", new TableInfo.Column("tagline", "TEXT", false, 0, "''", TableInfo.CREATED_FROM_ENTITY));
        _columnsMovies.put("homepage", new TableInfo.Column("homepage", "TEXT", false, 0, "''", TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMovies = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMovies = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMovies = new TableInfo("movies", _columnsMovies, _foreignKeysMovies, _indicesMovies);
        final TableInfo _existingMovies = TableInfo.read(db, "movies");
        if (!_infoMovies.equals(_existingMovies)) {
          return new RoomOpenHelper.ValidationResult(false, "movies(com.example.cinemaxapp.core.data.local.database.entity.MovieEntity).\n"
                  + " Expected:\n" + _infoMovies + "\n"
                  + " Found:\n" + _existingMovies);
        }
        final HashMap<String, TableInfo.Column> _columnsGenres = new HashMap<String, TableInfo.Column>(2);
        _columnsGenres.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGenres.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysGenres = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesGenres = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoGenres = new TableInfo("genres", _columnsGenres, _foreignKeysGenres, _indicesGenres);
        final TableInfo _existingGenres = TableInfo.read(db, "genres");
        if (!_infoGenres.equals(_existingGenres)) {
          return new RoomOpenHelper.ValidationResult(false, "genres(com.example.cinemaxapp.core.data.local.database.entity.GenreEntity).\n"
                  + " Expected:\n" + _infoGenres + "\n"
                  + " Found:\n" + _existingGenres);
        }
        final HashMap<String, TableInfo.Column> _columnsMovieGenreCrossRef = new HashMap<String, TableInfo.Column>(2);
        _columnsMovieGenreCrossRef.put("movie_id", new TableInfo.Column("movie_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieGenreCrossRef.put("genre_id", new TableInfo.Column("genre_id", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMovieGenreCrossRef = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMovieGenreCrossRef = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMovieGenreCrossRef = new TableInfo("movie_genre_cross_ref", _columnsMovieGenreCrossRef, _foreignKeysMovieGenreCrossRef, _indicesMovieGenreCrossRef);
        final TableInfo _existingMovieGenreCrossRef = TableInfo.read(db, "movie_genre_cross_ref");
        if (!_infoMovieGenreCrossRef.equals(_existingMovieGenreCrossRef)) {
          return new RoomOpenHelper.ValidationResult(false, "movie_genre_cross_ref(com.example.cinemaxapp.core.data.local.database.entity.MovieGenreCrossRef).\n"
                  + " Expected:\n" + _infoMovieGenreCrossRef + "\n"
                  + " Found:\n" + _existingMovieGenreCrossRef);
        }
        final HashMap<String, TableInfo.Column> _columnsNowPlayingMoviesRef = new HashMap<String, TableInfo.Column>(1);
        _columnsNowPlayingMoviesRef.put("movie_id", new TableInfo.Column("movie_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysNowPlayingMoviesRef = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesNowPlayingMoviesRef = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoNowPlayingMoviesRef = new TableInfo("now_playing_movies_ref", _columnsNowPlayingMoviesRef, _foreignKeysNowPlayingMoviesRef, _indicesNowPlayingMoviesRef);
        final TableInfo _existingNowPlayingMoviesRef = TableInfo.read(db, "now_playing_movies_ref");
        if (!_infoNowPlayingMoviesRef.equals(_existingNowPlayingMoviesRef)) {
          return new RoomOpenHelper.ValidationResult(false, "now_playing_movies_ref(com.example.cinemaxapp.core.data.local.database.entity.NowPlayingMovieRef).\n"
                  + " Expected:\n" + _infoNowPlayingMoviesRef + "\n"
                  + " Found:\n" + _existingNowPlayingMoviesRef);
        }
        final HashMap<String, TableInfo.Column> _columnsCredits = new HashMap<String, TableInfo.Column>(3);
        _columnsCredits.put("person_id", new TableInfo.Column("person_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCredits.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCredits.put("profile_path", new TableInfo.Column("profile_path", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCredits = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCredits = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCredits = new TableInfo("credits", _columnsCredits, _foreignKeysCredits, _indicesCredits);
        final TableInfo _existingCredits = TableInfo.read(db, "credits");
        if (!_infoCredits.equals(_existingCredits)) {
          return new RoomOpenHelper.ValidationResult(false, "credits(com.example.cinemaxapp.core.data.local.database.entity.CreditEntity).\n"
                  + " Expected:\n" + _infoCredits + "\n"
                  + " Found:\n" + _existingCredits);
        }
        final HashMap<String, TableInfo.Column> _columnsMovieCreditRef = new HashMap<String, TableInfo.Column>(8);
        _columnsMovieCreditRef.put("credit_id", new TableInfo.Column("credit_id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieCreditRef.put("movie_id", new TableInfo.Column("movie_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieCreditRef.put("person_id", new TableInfo.Column("person_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieCreditRef.put("credit_type", new TableInfo.Column("credit_type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieCreditRef.put("character", new TableInfo.Column("character", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieCreditRef.put("job", new TableInfo.Column("job", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieCreditRef.put("department", new TableInfo.Column("department", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieCreditRef.put("cast_order", new TableInfo.Column("cast_order", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMovieCreditRef = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysMovieCreditRef.add(new TableInfo.ForeignKey("movies", "CASCADE", "NO ACTION", Arrays.asList("movie_id"), Arrays.asList("id")));
        _foreignKeysMovieCreditRef.add(new TableInfo.ForeignKey("credits", "CASCADE", "NO ACTION", Arrays.asList("person_id"), Arrays.asList("person_id")));
        final HashSet<TableInfo.Index> _indicesMovieCreditRef = new HashSet<TableInfo.Index>(2);
        _indicesMovieCreditRef.add(new TableInfo.Index("index_movie_credit_ref_movie_id", false, Arrays.asList("movie_id"), Arrays.asList("ASC")));
        _indicesMovieCreditRef.add(new TableInfo.Index("index_movie_credit_ref_person_id", false, Arrays.asList("person_id"), Arrays.asList("ASC")));
        final TableInfo _infoMovieCreditRef = new TableInfo("movie_credit_ref", _columnsMovieCreditRef, _foreignKeysMovieCreditRef, _indicesMovieCreditRef);
        final TableInfo _existingMovieCreditRef = TableInfo.read(db, "movie_credit_ref");
        if (!_infoMovieCreditRef.equals(_existingMovieCreditRef)) {
          return new RoomOpenHelper.ValidationResult(false, "movie_credit_ref(com.example.cinemaxapp.core.data.local.database.entity.MovieCreditRef).\n"
                  + " Expected:\n" + _infoMovieCreditRef + "\n"
                  + " Found:\n" + _existingMovieCreditRef);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "ca87a252bcfc60904efbf6ab6b6f3be9", "5e59779ae35880e74cceb19094f1aa14");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "movies","genres","movie_genre_cross_ref","now_playing_movies_ref","credits","movie_credit_ref");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `movies`");
      _db.execSQL("DELETE FROM `genres`");
      _db.execSQL("DELETE FROM `movie_genre_cross_ref`");
      _db.execSQL("DELETE FROM `now_playing_movies_ref`");
      _db.execSQL("DELETE FROM `credits`");
      _db.execSQL("DELETE FROM `movie_credit_ref`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(MovieDao.class, MovieDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(GenreDao.class, GenreDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CreditDao.class, CreditDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public MovieDao movieDao() {
    if (_movieDao != null) {
      return _movieDao;
    } else {
      synchronized(this) {
        if(_movieDao == null) {
          _movieDao = new MovieDao_Impl(this);
        }
        return _movieDao;
      }
    }
  }

  @Override
  public GenreDao genreDao() {
    if (_genreDao != null) {
      return _genreDao;
    } else {
      synchronized(this) {
        if(_genreDao == null) {
          _genreDao = new GenreDao_Impl(this);
        }
        return _genreDao;
      }
    }
  }

  @Override
  public CreditDao creditDao() {
    if (_creditDao != null) {
      return _creditDao;
    } else {
      synchronized(this) {
        if(_creditDao == null) {
          _creditDao = new CreditDao_Impl(this);
        }
        return _creditDao;
      }
    }
  }
}
