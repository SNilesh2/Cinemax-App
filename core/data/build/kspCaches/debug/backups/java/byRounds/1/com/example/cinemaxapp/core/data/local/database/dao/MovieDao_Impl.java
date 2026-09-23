package com.example.cinemaxapp.core.data.local.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.LongSparseArray;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.EntityUpsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.RelationUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.cinemaxapp.core.data.local.database.entity.GenreEntity;
import com.example.cinemaxapp.core.data.local.database.entity.GenreWithMovies;
import com.example.cinemaxapp.core.data.local.database.entity.MovieEntity;
import com.example.cinemaxapp.core.data.local.database.entity.MovieGenreCrossRef;
import com.example.cinemaxapp.core.data.local.database.entity.MovieWithGenres;
import com.example.cinemaxapp.core.data.local.database.entity.NowPlayingMovieRef;
import com.example.cinemaxapp.core.data.local.database.entity.WishlistMovieRef;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class MovieDao_Impl implements MovieDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<WishlistMovieRef> __insertionAdapterOfWishlistMovieRef;

  private final SharedSQLiteStatement __preparedStmtOfClearNowPlayingRefs;

  private final SharedSQLiteStatement __preparedStmtOfClearCrossRefsForGenre;

  private final SharedSQLiteStatement __preparedStmtOfDeleteWishlistRef;

  private final EntityUpsertionAdapter<MovieEntity> __upsertionAdapterOfMovieEntity;

  private final EntityUpsertionAdapter<NowPlayingMovieRef> __upsertionAdapterOfNowPlayingMovieRef;

  private final EntityUpsertionAdapter<MovieGenreCrossRef> __upsertionAdapterOfMovieGenreCrossRef;

  public MovieDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWishlistMovieRef = new EntityInsertionAdapter<WishlistMovieRef>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `wishlist_movies_ref` (`movie_id`,`added_at`) VALUES (?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final WishlistMovieRef entity) {
        statement.bindLong(1, entity.getMovieId());
        statement.bindLong(2, entity.getAddedAt());
      }
    };
    this.__preparedStmtOfClearNowPlayingRefs = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM now_playing_movies_ref";
        return _query;
      }
    };
    this.__preparedStmtOfClearCrossRefsForGenre = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM movie_genre_cross_ref WHERE genre_id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteWishlistRef = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM wishlist_movies_ref WHERE movie_id = ?";
        return _query;
      }
    };
    this.__upsertionAdapterOfMovieEntity = new EntityUpsertionAdapter<MovieEntity>(new EntityInsertionAdapter<MovieEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT INTO `movies` (`id`,`title`,`backdrop_path`,`poster_path`,`release_date`,`vote_average`,`overview`,`runtime`,`tagline`,`homepage`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MovieEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        if (entity.getBackdropPath() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getBackdropPath());
        }
        if (entity.getPosterPath() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getPosterPath());
        }
        if (entity.getReleaseDate() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getReleaseDate());
        }
        if (entity.getVoteAverage() == null) {
          statement.bindNull(6);
        } else {
          statement.bindDouble(6, entity.getVoteAverage());
        }
        if (entity.getOverview() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getOverview());
        }
        if (entity.getRuntime() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getRuntime());
        }
        if (entity.getTagline() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getTagline());
        }
        if (entity.getHomepage() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getHomepage());
        }
      }
    }, new EntityDeletionOrUpdateAdapter<MovieEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE `movies` SET `id` = ?,`title` = ?,`backdrop_path` = ?,`poster_path` = ?,`release_date` = ?,`vote_average` = ?,`overview` = ?,`runtime` = ?,`tagline` = ?,`homepage` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MovieEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        if (entity.getBackdropPath() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getBackdropPath());
        }
        if (entity.getPosterPath() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getPosterPath());
        }
        if (entity.getReleaseDate() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getReleaseDate());
        }
        if (entity.getVoteAverage() == null) {
          statement.bindNull(6);
        } else {
          statement.bindDouble(6, entity.getVoteAverage());
        }
        if (entity.getOverview() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getOverview());
        }
        if (entity.getRuntime() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getRuntime());
        }
        if (entity.getTagline() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getTagline());
        }
        if (entity.getHomepage() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getHomepage());
        }
        statement.bindLong(11, entity.getId());
      }
    });
    this.__upsertionAdapterOfNowPlayingMovieRef = new EntityUpsertionAdapter<NowPlayingMovieRef>(new EntityInsertionAdapter<NowPlayingMovieRef>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT INTO `now_playing_movies_ref` (`movie_id`) VALUES (?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final NowPlayingMovieRef entity) {
        statement.bindLong(1, entity.getMovieId());
      }
    }, new EntityDeletionOrUpdateAdapter<NowPlayingMovieRef>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE `now_playing_movies_ref` SET `movie_id` = ? WHERE `movie_id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final NowPlayingMovieRef entity) {
        statement.bindLong(1, entity.getMovieId());
        statement.bindLong(2, entity.getMovieId());
      }
    });
    this.__upsertionAdapterOfMovieGenreCrossRef = new EntityUpsertionAdapter<MovieGenreCrossRef>(new EntityInsertionAdapter<MovieGenreCrossRef>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT INTO `movie_genre_cross_ref` (`movie_id`,`genre_id`) VALUES (?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MovieGenreCrossRef entity) {
        statement.bindLong(1, entity.getMovieId());
        statement.bindLong(2, entity.getGenreId());
      }
    }, new EntityDeletionOrUpdateAdapter<MovieGenreCrossRef>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE `movie_genre_cross_ref` SET `movie_id` = ?,`genre_id` = ? WHERE `movie_id` = ? AND `genre_id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MovieGenreCrossRef entity) {
        statement.bindLong(1, entity.getMovieId());
        statement.bindLong(2, entity.getGenreId());
        statement.bindLong(3, entity.getMovieId());
        statement.bindLong(4, entity.getGenreId());
      }
    });
  }

  @Override
  public Object insertWishlistRef(final WishlistMovieRef ref,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfWishlistMovieRef.insert(ref);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object clearNowPlayingRefs(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearNowPlayingRefs.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfClearNowPlayingRefs.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object clearCrossRefsForGenre(final int genreId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearCrossRefsForGenre.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, genreId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfClearCrossRefsForGenre.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteWishlistRef(final int movieId, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteWishlistRef.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, movieId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteWishlistRef.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object upsertMovies(final List<MovieEntity> movies,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __upsertionAdapterOfMovieEntity.upsert(movies);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object upsertNowPlayingRefs(final List<NowPlayingMovieRef> refs,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __upsertionAdapterOfNowPlayingMovieRef.upsert(refs);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object upsertCrossRefs(final List<MovieGenreCrossRef> crossRefs,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __upsertionAdapterOfMovieGenreCrossRef.upsert(crossRefs);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<MovieEntity>> getNowPlayingMovies() {
    final String _sql = "\n"
            + "        SELECT m.*\n"
            + "        FROM movies m\n"
            + "        INNER JOIN now_playing_movies_ref ref ON m.id = ref.movie_id\n"
            + "        ORDER BY m.id ASC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"movies",
        "now_playing_movies_ref"}, new Callable<List<MovieEntity>>() {
      @Override
      @NonNull
      public List<MovieEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfBackdropPath = CursorUtil.getColumnIndexOrThrow(_cursor, "backdrop_path");
          final int _cursorIndexOfPosterPath = CursorUtil.getColumnIndexOrThrow(_cursor, "poster_path");
          final int _cursorIndexOfReleaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "release_date");
          final int _cursorIndexOfVoteAverage = CursorUtil.getColumnIndexOrThrow(_cursor, "vote_average");
          final int _cursorIndexOfOverview = CursorUtil.getColumnIndexOrThrow(_cursor, "overview");
          final int _cursorIndexOfRuntime = CursorUtil.getColumnIndexOrThrow(_cursor, "runtime");
          final int _cursorIndexOfTagline = CursorUtil.getColumnIndexOrThrow(_cursor, "tagline");
          final int _cursorIndexOfHomepage = CursorUtil.getColumnIndexOrThrow(_cursor, "homepage");
          final List<MovieEntity> _result = new ArrayList<MovieEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MovieEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpBackdropPath;
            if (_cursor.isNull(_cursorIndexOfBackdropPath)) {
              _tmpBackdropPath = null;
            } else {
              _tmpBackdropPath = _cursor.getString(_cursorIndexOfBackdropPath);
            }
            final String _tmpPosterPath;
            if (_cursor.isNull(_cursorIndexOfPosterPath)) {
              _tmpPosterPath = null;
            } else {
              _tmpPosterPath = _cursor.getString(_cursorIndexOfPosterPath);
            }
            final String _tmpReleaseDate;
            if (_cursor.isNull(_cursorIndexOfReleaseDate)) {
              _tmpReleaseDate = null;
            } else {
              _tmpReleaseDate = _cursor.getString(_cursorIndexOfReleaseDate);
            }
            final Double _tmpVoteAverage;
            if (_cursor.isNull(_cursorIndexOfVoteAverage)) {
              _tmpVoteAverage = null;
            } else {
              _tmpVoteAverage = _cursor.getDouble(_cursorIndexOfVoteAverage);
            }
            final String _tmpOverview;
            if (_cursor.isNull(_cursorIndexOfOverview)) {
              _tmpOverview = null;
            } else {
              _tmpOverview = _cursor.getString(_cursorIndexOfOverview);
            }
            final Integer _tmpRuntime;
            if (_cursor.isNull(_cursorIndexOfRuntime)) {
              _tmpRuntime = null;
            } else {
              _tmpRuntime = _cursor.getInt(_cursorIndexOfRuntime);
            }
            final String _tmpTagline;
            if (_cursor.isNull(_cursorIndexOfTagline)) {
              _tmpTagline = null;
            } else {
              _tmpTagline = _cursor.getString(_cursorIndexOfTagline);
            }
            final String _tmpHomepage;
            if (_cursor.isNull(_cursorIndexOfHomepage)) {
              _tmpHomepage = null;
            } else {
              _tmpHomepage = _cursor.getString(_cursorIndexOfHomepage);
            }
            _item = new MovieEntity(_tmpId,_tmpTitle,_tmpBackdropPath,_tmpPosterPath,_tmpReleaseDate,_tmpVoteAverage,_tmpOverview,_tmpRuntime,_tmpTagline,_tmpHomepage);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<GenreWithMovies> getGenreWithMovies(final int genreId) {
    final String _sql = "SELECT * FROM genres WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, genreId);
    return CoroutinesRoom.createFlow(__db, true, new String[] {"movie_genre_cross_ref", "movies",
        "genres"}, new Callable<GenreWithMovies>() {
      @Override
      @Nullable
      public GenreWithMovies call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
            final LongSparseArray<ArrayList<MovieEntity>> _collectionMovies = new LongSparseArray<ArrayList<MovieEntity>>();
            while (_cursor.moveToNext()) {
              final long _tmpKey;
              _tmpKey = _cursor.getLong(_cursorIndexOfId);
              if (!_collectionMovies.containsKey(_tmpKey)) {
                _collectionMovies.put(_tmpKey, new ArrayList<MovieEntity>());
              }
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshipmoviesAscomExampleCinemaxappCoreDataLocalDatabaseEntityMovieEntity(_collectionMovies);
            final GenreWithMovies _result;
            if (_cursor.moveToFirst()) {
              final GenreEntity _tmpGenre;
              final int _tmpId;
              _tmpId = _cursor.getInt(_cursorIndexOfId);
              final String _tmpName;
              _tmpName = _cursor.getString(_cursorIndexOfName);
              _tmpGenre = new GenreEntity(_tmpId,_tmpName);
              final ArrayList<MovieEntity> _tmpMoviesCollection;
              final long _tmpKey_1;
              _tmpKey_1 = _cursor.getLong(_cursorIndexOfId);
              _tmpMoviesCollection = _collectionMovies.get(_tmpKey_1);
              _result = new GenreWithMovies(_tmpGenre,_tmpMoviesCollection);
            } else {
              _result = null;
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<MovieWithGenres> getMovieWithGenres(final int movieId) {
    final String _sql = "SELECT * FROM movies WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, movieId);
    return CoroutinesRoom.createFlow(__db, true, new String[] {"movie_genre_cross_ref", "genres",
        "movies"}, new Callable<MovieWithGenres>() {
      @Override
      @Nullable
      public MovieWithGenres call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
            final int _cursorIndexOfBackdropPath = CursorUtil.getColumnIndexOrThrow(_cursor, "backdrop_path");
            final int _cursorIndexOfPosterPath = CursorUtil.getColumnIndexOrThrow(_cursor, "poster_path");
            final int _cursorIndexOfReleaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "release_date");
            final int _cursorIndexOfVoteAverage = CursorUtil.getColumnIndexOrThrow(_cursor, "vote_average");
            final int _cursorIndexOfOverview = CursorUtil.getColumnIndexOrThrow(_cursor, "overview");
            final int _cursorIndexOfRuntime = CursorUtil.getColumnIndexOrThrow(_cursor, "runtime");
            final int _cursorIndexOfTagline = CursorUtil.getColumnIndexOrThrow(_cursor, "tagline");
            final int _cursorIndexOfHomepage = CursorUtil.getColumnIndexOrThrow(_cursor, "homepage");
            final LongSparseArray<ArrayList<GenreEntity>> _collectionGenres = new LongSparseArray<ArrayList<GenreEntity>>();
            while (_cursor.moveToNext()) {
              final long _tmpKey;
              _tmpKey = _cursor.getLong(_cursorIndexOfId);
              if (!_collectionGenres.containsKey(_tmpKey)) {
                _collectionGenres.put(_tmpKey, new ArrayList<GenreEntity>());
              }
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshipgenresAscomExampleCinemaxappCoreDataLocalDatabaseEntityGenreEntity(_collectionGenres);
            final MovieWithGenres _result;
            if (_cursor.moveToFirst()) {
              final MovieEntity _tmpMovie;
              final int _tmpId;
              _tmpId = _cursor.getInt(_cursorIndexOfId);
              final String _tmpTitle;
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
              final String _tmpBackdropPath;
              if (_cursor.isNull(_cursorIndexOfBackdropPath)) {
                _tmpBackdropPath = null;
              } else {
                _tmpBackdropPath = _cursor.getString(_cursorIndexOfBackdropPath);
              }
              final String _tmpPosterPath;
              if (_cursor.isNull(_cursorIndexOfPosterPath)) {
                _tmpPosterPath = null;
              } else {
                _tmpPosterPath = _cursor.getString(_cursorIndexOfPosterPath);
              }
              final String _tmpReleaseDate;
              if (_cursor.isNull(_cursorIndexOfReleaseDate)) {
                _tmpReleaseDate = null;
              } else {
                _tmpReleaseDate = _cursor.getString(_cursorIndexOfReleaseDate);
              }
              final Double _tmpVoteAverage;
              if (_cursor.isNull(_cursorIndexOfVoteAverage)) {
                _tmpVoteAverage = null;
              } else {
                _tmpVoteAverage = _cursor.getDouble(_cursorIndexOfVoteAverage);
              }
              final String _tmpOverview;
              if (_cursor.isNull(_cursorIndexOfOverview)) {
                _tmpOverview = null;
              } else {
                _tmpOverview = _cursor.getString(_cursorIndexOfOverview);
              }
              final Integer _tmpRuntime;
              if (_cursor.isNull(_cursorIndexOfRuntime)) {
                _tmpRuntime = null;
              } else {
                _tmpRuntime = _cursor.getInt(_cursorIndexOfRuntime);
              }
              final String _tmpTagline;
              if (_cursor.isNull(_cursorIndexOfTagline)) {
                _tmpTagline = null;
              } else {
                _tmpTagline = _cursor.getString(_cursorIndexOfTagline);
              }
              final String _tmpHomepage;
              if (_cursor.isNull(_cursorIndexOfHomepage)) {
                _tmpHomepage = null;
              } else {
                _tmpHomepage = _cursor.getString(_cursorIndexOfHomepage);
              }
              _tmpMovie = new MovieEntity(_tmpId,_tmpTitle,_tmpBackdropPath,_tmpPosterPath,_tmpReleaseDate,_tmpVoteAverage,_tmpOverview,_tmpRuntime,_tmpTagline,_tmpHomepage);
              final ArrayList<GenreEntity> _tmpGenresCollection;
              final long _tmpKey_1;
              _tmpKey_1 = _cursor.getLong(_cursorIndexOfId);
              _tmpGenresCollection = _collectionGenres.get(_tmpKey_1);
              _result = new MovieWithGenres(_tmpMovie,_tmpGenresCollection);
            } else {
              _result = null;
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<Boolean> isMovieWishlisted(final int movieId) {
    final String _sql = "SELECT EXISTS(SELECT 1 FROM wishlist_movies_ref WHERE movie_id = ?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, movieId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"wishlist_movies_ref"}, new Callable<Boolean>() {
      @Override
      @NonNull
      public Boolean call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Boolean _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp != 0;
          } else {
            _result = false;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object isMovieWishlistedDirect(final int movieId,
      final Continuation<? super Boolean> $completion) {
    final String _sql = "SELECT EXISTS(SELECT 1 FROM wishlist_movies_ref WHERE movie_id = ?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, movieId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Boolean>() {
      @Override
      @NonNull
      public Boolean call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Boolean _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp != 0;
          } else {
            _result = false;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<MovieWithGenres>> getWishlistMoviesWithGenres() {
    final String _sql = "\n"
            + "        SELECT m.* FROM movies m\n"
            + "        INNER JOIN wishlist_movies_ref ref ON m.id = ref.movie_id\n"
            + "        ORDER BY ref.added_at DESC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, true, new String[] {"movie_genre_cross_ref", "genres",
        "movies", "wishlist_movies_ref"}, new Callable<List<MovieWithGenres>>() {
      @Override
      @NonNull
      public List<MovieWithGenres> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
            final int _cursorIndexOfBackdropPath = CursorUtil.getColumnIndexOrThrow(_cursor, "backdrop_path");
            final int _cursorIndexOfPosterPath = CursorUtil.getColumnIndexOrThrow(_cursor, "poster_path");
            final int _cursorIndexOfReleaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "release_date");
            final int _cursorIndexOfVoteAverage = CursorUtil.getColumnIndexOrThrow(_cursor, "vote_average");
            final int _cursorIndexOfOverview = CursorUtil.getColumnIndexOrThrow(_cursor, "overview");
            final int _cursorIndexOfRuntime = CursorUtil.getColumnIndexOrThrow(_cursor, "runtime");
            final int _cursorIndexOfTagline = CursorUtil.getColumnIndexOrThrow(_cursor, "tagline");
            final int _cursorIndexOfHomepage = CursorUtil.getColumnIndexOrThrow(_cursor, "homepage");
            final LongSparseArray<ArrayList<GenreEntity>> _collectionGenres = new LongSparseArray<ArrayList<GenreEntity>>();
            while (_cursor.moveToNext()) {
              final long _tmpKey;
              _tmpKey = _cursor.getLong(_cursorIndexOfId);
              if (!_collectionGenres.containsKey(_tmpKey)) {
                _collectionGenres.put(_tmpKey, new ArrayList<GenreEntity>());
              }
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshipgenresAscomExampleCinemaxappCoreDataLocalDatabaseEntityGenreEntity(_collectionGenres);
            final List<MovieWithGenres> _result = new ArrayList<MovieWithGenres>(_cursor.getCount());
            while (_cursor.moveToNext()) {
              final MovieWithGenres _item;
              final MovieEntity _tmpMovie;
              final int _tmpId;
              _tmpId = _cursor.getInt(_cursorIndexOfId);
              final String _tmpTitle;
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
              final String _tmpBackdropPath;
              if (_cursor.isNull(_cursorIndexOfBackdropPath)) {
                _tmpBackdropPath = null;
              } else {
                _tmpBackdropPath = _cursor.getString(_cursorIndexOfBackdropPath);
              }
              final String _tmpPosterPath;
              if (_cursor.isNull(_cursorIndexOfPosterPath)) {
                _tmpPosterPath = null;
              } else {
                _tmpPosterPath = _cursor.getString(_cursorIndexOfPosterPath);
              }
              final String _tmpReleaseDate;
              if (_cursor.isNull(_cursorIndexOfReleaseDate)) {
                _tmpReleaseDate = null;
              } else {
                _tmpReleaseDate = _cursor.getString(_cursorIndexOfReleaseDate);
              }
              final Double _tmpVoteAverage;
              if (_cursor.isNull(_cursorIndexOfVoteAverage)) {
                _tmpVoteAverage = null;
              } else {
                _tmpVoteAverage = _cursor.getDouble(_cursorIndexOfVoteAverage);
              }
              final String _tmpOverview;
              if (_cursor.isNull(_cursorIndexOfOverview)) {
                _tmpOverview = null;
              } else {
                _tmpOverview = _cursor.getString(_cursorIndexOfOverview);
              }
              final Integer _tmpRuntime;
              if (_cursor.isNull(_cursorIndexOfRuntime)) {
                _tmpRuntime = null;
              } else {
                _tmpRuntime = _cursor.getInt(_cursorIndexOfRuntime);
              }
              final String _tmpTagline;
              if (_cursor.isNull(_cursorIndexOfTagline)) {
                _tmpTagline = null;
              } else {
                _tmpTagline = _cursor.getString(_cursorIndexOfTagline);
              }
              final String _tmpHomepage;
              if (_cursor.isNull(_cursorIndexOfHomepage)) {
                _tmpHomepage = null;
              } else {
                _tmpHomepage = _cursor.getString(_cursorIndexOfHomepage);
              }
              _tmpMovie = new MovieEntity(_tmpId,_tmpTitle,_tmpBackdropPath,_tmpPosterPath,_tmpReleaseDate,_tmpVoteAverage,_tmpOverview,_tmpRuntime,_tmpTagline,_tmpHomepage);
              final ArrayList<GenreEntity> _tmpGenresCollection;
              final long _tmpKey_1;
              _tmpKey_1 = _cursor.getLong(_cursorIndexOfId);
              _tmpGenresCollection = _collectionGenres.get(_tmpKey_1);
              _item = new MovieWithGenres(_tmpMovie,_tmpGenresCollection);
              _result.add(_item);
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private void __fetchRelationshipmoviesAscomExampleCinemaxappCoreDataLocalDatabaseEntityMovieEntity(
      @NonNull final LongSparseArray<ArrayList<MovieEntity>> _map) {
    if (_map.isEmpty()) {
      return;
    }
    if (_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      RelationUtil.recursiveFetchLongSparseArray(_map, true, (map) -> {
        __fetchRelationshipmoviesAscomExampleCinemaxappCoreDataLocalDatabaseEntityMovieEntity(map);
        return Unit.INSTANCE;
      });
      return;
    }
    final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `movies`.`id` AS `id`,`movies`.`title` AS `title`,`movies`.`backdrop_path` AS `backdrop_path`,`movies`.`poster_path` AS `poster_path`,`movies`.`release_date` AS `release_date`,`movies`.`vote_average` AS `vote_average`,`movies`.`overview` AS `overview`,`movies`.`runtime` AS `runtime`,`movies`.`tagline` AS `tagline`,`movies`.`homepage` AS `homepage`,_junction.`genre_id` FROM `movie_genre_cross_ref` AS _junction INNER JOIN `movies` ON (_junction.`movie_id` = `movies`.`id`) WHERE _junction.`genre_id` IN (");
    final int _inputSize = _map.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int i = 0; i < _map.size(); i++) {
      final long _item = _map.keyAt(i);
      _stmt.bindLong(_argIndex, _item);
      _argIndex++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      // _junction.genre_id;
      final int _itemKeyIndex = 10;
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfId = 0;
      final int _cursorIndexOfTitle = 1;
      final int _cursorIndexOfBackdropPath = 2;
      final int _cursorIndexOfPosterPath = 3;
      final int _cursorIndexOfReleaseDate = 4;
      final int _cursorIndexOfVoteAverage = 5;
      final int _cursorIndexOfOverview = 6;
      final int _cursorIndexOfRuntime = 7;
      final int _cursorIndexOfTagline = 8;
      final int _cursorIndexOfHomepage = 9;
      while (_cursor.moveToNext()) {
        final long _tmpKey;
        _tmpKey = _cursor.getLong(_itemKeyIndex);
        final ArrayList<MovieEntity> _tmpRelation = _map.get(_tmpKey);
        if (_tmpRelation != null) {
          final MovieEntity _item_1;
          final int _tmpId;
          _tmpId = _cursor.getInt(_cursorIndexOfId);
          final String _tmpTitle;
          _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
          final String _tmpBackdropPath;
          if (_cursor.isNull(_cursorIndexOfBackdropPath)) {
            _tmpBackdropPath = null;
          } else {
            _tmpBackdropPath = _cursor.getString(_cursorIndexOfBackdropPath);
          }
          final String _tmpPosterPath;
          if (_cursor.isNull(_cursorIndexOfPosterPath)) {
            _tmpPosterPath = null;
          } else {
            _tmpPosterPath = _cursor.getString(_cursorIndexOfPosterPath);
          }
          final String _tmpReleaseDate;
          if (_cursor.isNull(_cursorIndexOfReleaseDate)) {
            _tmpReleaseDate = null;
          } else {
            _tmpReleaseDate = _cursor.getString(_cursorIndexOfReleaseDate);
          }
          final Double _tmpVoteAverage;
          if (_cursor.isNull(_cursorIndexOfVoteAverage)) {
            _tmpVoteAverage = null;
          } else {
            _tmpVoteAverage = _cursor.getDouble(_cursorIndexOfVoteAverage);
          }
          final String _tmpOverview;
          if (_cursor.isNull(_cursorIndexOfOverview)) {
            _tmpOverview = null;
          } else {
            _tmpOverview = _cursor.getString(_cursorIndexOfOverview);
          }
          final Integer _tmpRuntime;
          if (_cursor.isNull(_cursorIndexOfRuntime)) {
            _tmpRuntime = null;
          } else {
            _tmpRuntime = _cursor.getInt(_cursorIndexOfRuntime);
          }
          final String _tmpTagline;
          if (_cursor.isNull(_cursorIndexOfTagline)) {
            _tmpTagline = null;
          } else {
            _tmpTagline = _cursor.getString(_cursorIndexOfTagline);
          }
          final String _tmpHomepage;
          if (_cursor.isNull(_cursorIndexOfHomepage)) {
            _tmpHomepage = null;
          } else {
            _tmpHomepage = _cursor.getString(_cursorIndexOfHomepage);
          }
          _item_1 = new MovieEntity(_tmpId,_tmpTitle,_tmpBackdropPath,_tmpPosterPath,_tmpReleaseDate,_tmpVoteAverage,_tmpOverview,_tmpRuntime,_tmpTagline,_tmpHomepage);
          _tmpRelation.add(_item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }

  private void __fetchRelationshipgenresAscomExampleCinemaxappCoreDataLocalDatabaseEntityGenreEntity(
      @NonNull final LongSparseArray<ArrayList<GenreEntity>> _map) {
    if (_map.isEmpty()) {
      return;
    }
    if (_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      RelationUtil.recursiveFetchLongSparseArray(_map, true, (map) -> {
        __fetchRelationshipgenresAscomExampleCinemaxappCoreDataLocalDatabaseEntityGenreEntity(map);
        return Unit.INSTANCE;
      });
      return;
    }
    final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `genres`.`id` AS `id`,`genres`.`name` AS `name`,_junction.`movie_id` FROM `movie_genre_cross_ref` AS _junction INNER JOIN `genres` ON (_junction.`genre_id` = `genres`.`id`) WHERE _junction.`movie_id` IN (");
    final int _inputSize = _map.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int i = 0; i < _map.size(); i++) {
      final long _item = _map.keyAt(i);
      _stmt.bindLong(_argIndex, _item);
      _argIndex++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      // _junction.movie_id;
      final int _itemKeyIndex = 2;
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfId = 0;
      final int _cursorIndexOfName = 1;
      while (_cursor.moveToNext()) {
        final long _tmpKey;
        _tmpKey = _cursor.getLong(_itemKeyIndex);
        final ArrayList<GenreEntity> _tmpRelation = _map.get(_tmpKey);
        if (_tmpRelation != null) {
          final GenreEntity _item_1;
          final int _tmpId;
          _tmpId = _cursor.getInt(_cursorIndexOfId);
          final String _tmpName;
          _tmpName = _cursor.getString(_cursorIndexOfName);
          _item_1 = new GenreEntity(_tmpId,_tmpName);
          _tmpRelation.add(_item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
