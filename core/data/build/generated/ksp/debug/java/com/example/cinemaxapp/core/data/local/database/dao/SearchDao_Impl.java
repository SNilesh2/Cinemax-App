package com.example.cinemaxapp.core.data.local.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.LongSparseArray;
import androidx.paging.PagingSource;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.EntityUpsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomDatabaseKt;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.paging.LimitOffsetPagingSource;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.RelationUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.cinemaxapp.core.data.local.database.entity.CreditEntity;
import com.example.cinemaxapp.core.data.local.database.entity.GenreEntity;
import com.example.cinemaxapp.core.data.local.database.entity.MovieEntity;
import com.example.cinemaxapp.core.data.local.database.entity.MovieWithGenres;
import com.example.cinemaxapp.core.data.local.database.entity.SearchMovieRef;
import com.example.cinemaxapp.core.data.local.database.entity.SearchPersonRef;
import com.example.cinemaxapp.core.data.local.database.entity.SearchRemoteKeyEntity;
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
public final class SearchDao_Impl implements SearchDao {
  private final RoomDatabase __db;

  private final SharedSQLiteStatement __preparedStmtOfClearMovieRefsForQuery;

  private final SharedSQLiteStatement __preparedStmtOfClearPersonRefsForQuery;

  private final SharedSQLiteStatement __preparedStmtOfClearRemoteKeyForQuery;

  private final EntityUpsertionAdapter<SearchMovieRef> __upsertionAdapterOfSearchMovieRef;

  private final EntityUpsertionAdapter<SearchPersonRef> __upsertionAdapterOfSearchPersonRef;

  private final EntityUpsertionAdapter<SearchRemoteKeyEntity> __upsertionAdapterOfSearchRemoteKeyEntity;

  public SearchDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__preparedStmtOfClearMovieRefsForQuery = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM search_movies_ref WHERE `query` = ?";
        return _query;
      }
    };
    this.__preparedStmtOfClearPersonRefsForQuery = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM search_persons_ref WHERE `query` = ?";
        return _query;
      }
    };
    this.__preparedStmtOfClearRemoteKeyForQuery = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM search_remote_keys WHERE `query` = ?";
        return _query;
      }
    };
    this.__upsertionAdapterOfSearchMovieRef = new EntityUpsertionAdapter<SearchMovieRef>(new EntityInsertionAdapter<SearchMovieRef>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT INTO `search_movies_ref` (`query`,`movie_id`,`page`,`position`) VALUES (?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SearchMovieRef entity) {
        statement.bindString(1, entity.getQuery());
        statement.bindLong(2, entity.getMovieId());
        statement.bindLong(3, entity.getPage());
        statement.bindLong(4, entity.getPosition());
      }
    }, new EntityDeletionOrUpdateAdapter<SearchMovieRef>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE `search_movies_ref` SET `query` = ?,`movie_id` = ?,`page` = ?,`position` = ? WHERE `query` = ? AND `movie_id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SearchMovieRef entity) {
        statement.bindString(1, entity.getQuery());
        statement.bindLong(2, entity.getMovieId());
        statement.bindLong(3, entity.getPage());
        statement.bindLong(4, entity.getPosition());
        statement.bindString(5, entity.getQuery());
        statement.bindLong(6, entity.getMovieId());
      }
    });
    this.__upsertionAdapterOfSearchPersonRef = new EntityUpsertionAdapter<SearchPersonRef>(new EntityInsertionAdapter<SearchPersonRef>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT INTO `search_persons_ref` (`query`,`person_id`,`page`,`position`) VALUES (?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SearchPersonRef entity) {
        statement.bindString(1, entity.getQuery());
        statement.bindLong(2, entity.getPersonId());
        statement.bindLong(3, entity.getPage());
        statement.bindLong(4, entity.getPosition());
      }
    }, new EntityDeletionOrUpdateAdapter<SearchPersonRef>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE `search_persons_ref` SET `query` = ?,`person_id` = ?,`page` = ?,`position` = ? WHERE `query` = ? AND `person_id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SearchPersonRef entity) {
        statement.bindString(1, entity.getQuery());
        statement.bindLong(2, entity.getPersonId());
        statement.bindLong(3, entity.getPage());
        statement.bindLong(4, entity.getPosition());
        statement.bindString(5, entity.getQuery());
        statement.bindLong(6, entity.getPersonId());
      }
    });
    this.__upsertionAdapterOfSearchRemoteKeyEntity = new EntityUpsertionAdapter<SearchRemoteKeyEntity>(new EntityInsertionAdapter<SearchRemoteKeyEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT INTO `search_remote_keys` (`query`,`next_page`,`prev_page`) VALUES (?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SearchRemoteKeyEntity entity) {
        statement.bindString(1, entity.getQuery());
        if (entity.getNextPage() == null) {
          statement.bindNull(2);
        } else {
          statement.bindLong(2, entity.getNextPage());
        }
        if (entity.getPrevPage() == null) {
          statement.bindNull(3);
        } else {
          statement.bindLong(3, entity.getPrevPage());
        }
      }
    }, new EntityDeletionOrUpdateAdapter<SearchRemoteKeyEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE `search_remote_keys` SET `query` = ?,`next_page` = ?,`prev_page` = ? WHERE `query` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SearchRemoteKeyEntity entity) {
        statement.bindString(1, entity.getQuery());
        if (entity.getNextPage() == null) {
          statement.bindNull(2);
        } else {
          statement.bindLong(2, entity.getNextPage());
        }
        if (entity.getPrevPage() == null) {
          statement.bindNull(3);
        } else {
          statement.bindLong(3, entity.getPrevPage());
        }
        statement.bindString(4, entity.getQuery());
      }
    });
  }

  @Override
  public Object clearSearchCacheForQuery(final String query,
      final Continuation<? super Unit> $completion) {
    return RoomDatabaseKt.withTransaction(__db, (__cont) -> SearchDao.DefaultImpls.clearSearchCacheForQuery(SearchDao_Impl.this, query, __cont), $completion);
  }

  @Override
  public Object clearMovieRefsForQuery(final String query,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearMovieRefsForQuery.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, query);
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
          __preparedStmtOfClearMovieRefsForQuery.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object clearPersonRefsForQuery(final String query,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearPersonRefsForQuery.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, query);
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
          __preparedStmtOfClearPersonRefsForQuery.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object clearRemoteKeyForQuery(final String query,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearRemoteKeyForQuery.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, query);
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
          __preparedStmtOfClearRemoteKeyForQuery.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object upsertMovieRefs(final List<SearchMovieRef> refs,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __upsertionAdapterOfSearchMovieRef.upsert(refs);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object upsertPersonRefs(final List<SearchPersonRef> refs,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __upsertionAdapterOfSearchPersonRef.upsert(refs);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object upsertRemoteKey(final SearchRemoteKeyEntity remoteKey,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __upsertionAdapterOfSearchRemoteKeyEntity.upsert(remoteKey);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public PagingSource<Integer, MovieWithGenres> getMoviesForQueryPaging(final String query) {
    final String _sql = "\n"
            + "        SELECT m.* FROM movies m\n"
            + "        INNER JOIN search_movies_ref r ON m.id = r.movie_id\n"
            + "        WHERE r.`query` = ?\n"
            + "        ORDER BY r.page ASC, r.position ASC\n"
            + "        ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    return new LimitOffsetPagingSource<MovieWithGenres>(_statement, __db, "movie_genre_cross_ref", "genres", "movies", "search_movies_ref") {
      @Override
      @NonNull
      protected List<MovieWithGenres> convertRows(@NonNull final Cursor cursor) {
        final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(cursor, "id");
        final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(cursor, "title");
        final int _cursorIndexOfBackdropPath = CursorUtil.getColumnIndexOrThrow(cursor, "backdrop_path");
        final int _cursorIndexOfPosterPath = CursorUtil.getColumnIndexOrThrow(cursor, "poster_path");
        final int _cursorIndexOfReleaseDate = CursorUtil.getColumnIndexOrThrow(cursor, "release_date");
        final int _cursorIndexOfVoteAverage = CursorUtil.getColumnIndexOrThrow(cursor, "vote_average");
        final int _cursorIndexOfOverview = CursorUtil.getColumnIndexOrThrow(cursor, "overview");
        final int _cursorIndexOfRuntime = CursorUtil.getColumnIndexOrThrow(cursor, "runtime");
        final int _cursorIndexOfTagline = CursorUtil.getColumnIndexOrThrow(cursor, "tagline");
        final int _cursorIndexOfHomepage = CursorUtil.getColumnIndexOrThrow(cursor, "homepage");
        final LongSparseArray<ArrayList<GenreEntity>> _collectionGenres = new LongSparseArray<ArrayList<GenreEntity>>();
        while (cursor.moveToNext()) {
          final long _tmpKey;
          _tmpKey = cursor.getLong(_cursorIndexOfId);
          if (!_collectionGenres.containsKey(_tmpKey)) {
            _collectionGenres.put(_tmpKey, new ArrayList<GenreEntity>());
          }
        }
        cursor.moveToPosition(-1);
        __fetchRelationshipgenresAscomExampleCinemaxappCoreDataLocalDatabaseEntityGenreEntity(_collectionGenres);
        final List<MovieWithGenres> _result = new ArrayList<MovieWithGenres>(cursor.getCount());
        while (cursor.moveToNext()) {
          final MovieWithGenres _item;
          final MovieEntity _tmpMovie;
          final int _tmpId;
          _tmpId = cursor.getInt(_cursorIndexOfId);
          final String _tmpTitle;
          _tmpTitle = cursor.getString(_cursorIndexOfTitle);
          final String _tmpBackdropPath;
          if (cursor.isNull(_cursorIndexOfBackdropPath)) {
            _tmpBackdropPath = null;
          } else {
            _tmpBackdropPath = cursor.getString(_cursorIndexOfBackdropPath);
          }
          final String _tmpPosterPath;
          if (cursor.isNull(_cursorIndexOfPosterPath)) {
            _tmpPosterPath = null;
          } else {
            _tmpPosterPath = cursor.getString(_cursorIndexOfPosterPath);
          }
          final String _tmpReleaseDate;
          if (cursor.isNull(_cursorIndexOfReleaseDate)) {
            _tmpReleaseDate = null;
          } else {
            _tmpReleaseDate = cursor.getString(_cursorIndexOfReleaseDate);
          }
          final Double _tmpVoteAverage;
          if (cursor.isNull(_cursorIndexOfVoteAverage)) {
            _tmpVoteAverage = null;
          } else {
            _tmpVoteAverage = cursor.getDouble(_cursorIndexOfVoteAverage);
          }
          final String _tmpOverview;
          if (cursor.isNull(_cursorIndexOfOverview)) {
            _tmpOverview = null;
          } else {
            _tmpOverview = cursor.getString(_cursorIndexOfOverview);
          }
          final Integer _tmpRuntime;
          if (cursor.isNull(_cursorIndexOfRuntime)) {
            _tmpRuntime = null;
          } else {
            _tmpRuntime = cursor.getInt(_cursorIndexOfRuntime);
          }
          final String _tmpTagline;
          if (cursor.isNull(_cursorIndexOfTagline)) {
            _tmpTagline = null;
          } else {
            _tmpTagline = cursor.getString(_cursorIndexOfTagline);
          }
          final String _tmpHomepage;
          if (cursor.isNull(_cursorIndexOfHomepage)) {
            _tmpHomepage = null;
          } else {
            _tmpHomepage = cursor.getString(_cursorIndexOfHomepage);
          }
          _tmpMovie = new MovieEntity(_tmpId,_tmpTitle,_tmpBackdropPath,_tmpPosterPath,_tmpReleaseDate,_tmpVoteAverage,_tmpOverview,_tmpRuntime,_tmpTagline,_tmpHomepage);
          final ArrayList<GenreEntity> _tmpGenresCollection;
          final long _tmpKey_1;
          _tmpKey_1 = cursor.getLong(_cursorIndexOfId);
          _tmpGenresCollection = _collectionGenres.get(_tmpKey_1);
          _item = new MovieWithGenres(_tmpMovie,_tmpGenresCollection);
          _result.add(_item);
        }
        return _result;
      }
    };
  }

  @Override
  public Flow<List<CreditEntity>> getPersonsForQuery(final String query) {
    final String _sql = "\n"
            + "        SELECT c.* FROM credits c\n"
            + "        INNER JOIN search_persons_ref r ON c.person_id = r.person_id\n"
            + "        WHERE r.`query` = ?\n"
            + "        ORDER BY r.page ASC, r.position ASC\n"
            + "        ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"credits",
        "search_persons_ref"}, new Callable<List<CreditEntity>>() {
      @Override
      @NonNull
      public List<CreditEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPersonId = CursorUtil.getColumnIndexOrThrow(_cursor, "person_id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfProfilePath = CursorUtil.getColumnIndexOrThrow(_cursor, "profile_path");
          final List<CreditEntity> _result = new ArrayList<CreditEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CreditEntity _item;
            final int _tmpPersonId;
            _tmpPersonId = _cursor.getInt(_cursorIndexOfPersonId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpProfilePath;
            if (_cursor.isNull(_cursorIndexOfProfilePath)) {
              _tmpProfilePath = null;
            } else {
              _tmpProfilePath = _cursor.getString(_cursorIndexOfProfilePath);
            }
            _item = new CreditEntity(_tmpPersonId,_tmpName,_tmpProfilePath);
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
  public Object getRemoteKeyForQuery(final String query,
      final Continuation<? super SearchRemoteKeyEntity> $completion) {
    final String _sql = "SELECT * FROM search_remote_keys WHERE `query` = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<SearchRemoteKeyEntity>() {
      @Override
      @Nullable
      public SearchRemoteKeyEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfQuery = CursorUtil.getColumnIndexOrThrow(_cursor, "query");
          final int _cursorIndexOfNextPage = CursorUtil.getColumnIndexOrThrow(_cursor, "next_page");
          final int _cursorIndexOfPrevPage = CursorUtil.getColumnIndexOrThrow(_cursor, "prev_page");
          final SearchRemoteKeyEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpQuery;
            _tmpQuery = _cursor.getString(_cursorIndexOfQuery);
            final Integer _tmpNextPage;
            if (_cursor.isNull(_cursorIndexOfNextPage)) {
              _tmpNextPage = null;
            } else {
              _tmpNextPage = _cursor.getInt(_cursorIndexOfNextPage);
            }
            final Integer _tmpPrevPage;
            if (_cursor.isNull(_cursorIndexOfPrevPage)) {
              _tmpPrevPage = null;
            } else {
              _tmpPrevPage = _cursor.getInt(_cursorIndexOfPrevPage);
            }
            _result = new SearchRemoteKeyEntity(_tmpQuery,_tmpNextPage,_tmpPrevPage);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
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
